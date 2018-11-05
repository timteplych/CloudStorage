package ru.ttv.cloudstorage.service.remote;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ttv.cloudstorage.api.remote.FolderRemoteService;
import ru.ttv.cloudstorage.api.system.ApplicationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.nodetype.NodeType;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class FolderRemoteServiceBean implements FolderRemoteService {

    @Inject
    private ApplicationService applicationService;


    @Override
    @NotNull
    @SneakyThrows
    public List<String> getListFolderNameRoot(@Nullable final String folder) {
        final List<String> result = new ArrayList<>();
        Node root = null;
        if (folder == null || folder.isEmpty()) {
            root = applicationService.getRootNode();
        } else {
            root = applicationService.session().getNode(folder);
        }

        final NodeIterator nt = root.getNodes();
        while (nt.hasNext()) {
            final Node node = nt.nextNode();
            final NodeType nodeType = node.getPrimaryNodeType();
            final boolean isFolder = nodeType.isNodeType("nt:folder");
            if (isFolder) result.add(node.getName());
        }
        return result;
    }

    @Override
    @SneakyThrows
    public void createFolder(@Nullable final String folderName) {
        @Nullable final Node root = applicationService.getRootNode();
        if (root == null) return;
        String[] folderArr = folderName.split("/");
        String currentPath = "";
        Node currentNode = root;
        Session session = applicationService.session();
        if (folderArr.length == 1) {
            root.addNode(folderName, "nt:folder");
        } else {
            for (int i = 0; i <= folderArr.length - 1; i++) {
                if (folderArr[i].isEmpty()) continue;
                currentPath = currentPath + "/" + folderArr[i];
                if (session.nodeExists(currentPath)) {
                    currentNode = session.getNode(currentPath);
                } else {
                    currentNode = currentNode.addNode(folderArr[i], "nt:folder");
                }
            }
        }
        applicationService.save();
    }

    @Override
    @SneakyThrows
    public boolean folderExist(@Nullable final String folderName) {
        Session session = applicationService.session();
        return session.nodeExists(folderName);
    }

    @Override
    public void printListFolderNameRoot() {
        for (final String name : getListFolderNameRoot("")) {
            System.out.println(name);
        }
    }

    @Override
    @SneakyThrows
    public void clearRoot() {
        final Node root = applicationService.getRootNode();
        final NodeIterator nt = root.getNodes();
        while (nt.hasNext()) {
            final Node node = nt.nextNode();
            final NodeType nodeType = node.getPrimaryNodeType();
            final boolean isFolder = nodeType.isNodeType("nt:folder");
            if (isFolder) node.remove();
        }
    }

    @Override
    public void removeFolder(@Nullable final String folderName) {
        String fld = folderName;
        if (folderName == null || folderName.isEmpty()) return;
        if (!folderName.startsWith("/")) {
            fld = "/" + folderName;
        }
        Node node = null;
        try {
            node = applicationService.session().getNode(fld);
            node.remove();
        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        applicationService.save();
    }

}
