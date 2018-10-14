package ru.ttv.cloudstorage.service.remote;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ttv.cloudstorage.api.remote.FileRemoteService;
import ru.ttv.cloudstorage.api.system.ApplicationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jcr.Binary;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.nodetype.NodeType;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class FileRemoteServiceBean implements FileRemoteService {

    @Inject
    private ApplicationService applicationService;

    @Nullable
    @Override
    @SneakyThrows
    public byte[] readData(String name) {
        if(name == null || name.isEmpty()) return new byte[]{};
        final Node root = applicationService.getRootNode();
        if(root == null) return new byte[]{};
        final Node node = root.getNode(name);
        final Binary binary = node.getProperty("jcr:data").getBinary();
        return IOUtils.toByteArray(binary.getStream());
    }

    @Override
    @SneakyThrows
    public boolean exist(String name) {
        if(name == null || name.isEmpty()) return false;
        final Node root = applicationService.getRootNode();
        if(root == null) return false;
        return root.hasNode(name);
    }

    @Override
    @SneakyThrows
    public void remove(@Nullable final String name) {
        if(name == null || name.isEmpty()) return;
        final Node root = applicationService.getRootNode();
        if(root == null) return;
        final Node node = root.getNode(name);
        node.remove();
    }

    @Override
    @SneakyThrows
    public void createTextFile(@Nullable final String fileName,@Nullable final String text) {
        if(text == null) return;
        writeData(fileName, text.getBytes());
    }

    @Override
    @SneakyThrows
    public void writeData(@Nullable final String fileName, byte[] data) {
        if(fileName == null || fileName.isEmpty()) return;
        final Session session = applicationService.session();
        if(session == null) return;
        final Node root = session.getRootNode();
        final Node file = root.addNode(fileName,"nt:file");
        final Node contentNode = file.addNode("jcr:content", "nt:resource");
        final ByteArrayInputStream stream = new ByteArrayInputStream(data);
        final Binary binary = session.getValueFactory().createBinary(stream);
        contentNode.setProperty("jcr:data", binary);
        final Calendar created = Calendar.getInstance();
        contentNode.setProperty("jcr:lastModified", created);
    }

    @NotNull
    @Override
    public List<String> getListFileNameRoot(String folder) {
        Node root = null;
        try {
            if(folder == null || folder.isEmpty()){
                root = applicationService.getRootNode();
            }else{
                final Session session = applicationService.session();
                root = session.getNode(folder);
            }

            if(root == null) return Collections.emptyList();
            final List<String> result = new ArrayList<>();
            final NodeIterator nt = root.getNodes();
            while (nt.hasNext()){
                final Node node = nt.nextNode();
                final NodeType nodeType = node.getPrimaryNodeType();
                final boolean isFile = nodeType.isNodeType("nt:file");
                if(isFile) result.add(node.getName());
            }
            return result;
        }catch (final Exception e){
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    @Override
    public void printListFileNameRoot() {
        for(final String name: getListFileNameRoot("")){
            System.out.println(name);
        }
    }

    @Override
    @SneakyThrows
    public void clearRoot() {
        final Node root = applicationService.getRootNode();
        final NodeIterator nt = root.getNodes();
        while (nt.hasNext()){
            final Node node = nt.nextNode();
            final NodeType nodeType = node.getPrimaryNodeType();
            final boolean isFile = nodeType.isNodeType("nt:file");
            if(isFile) node.remove();
        }
    }
}
