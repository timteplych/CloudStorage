package ru.ttv.cloudstorage.webapp.document;

import org.jetbrains.annotations.Nullable;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import ru.ttv.cloudstorage.api.remote.FileRemoteService;
import ru.ttv.cloudstorage.api.remote.FolderRemoteService;
import ru.ttv.cloudstorage.webapp.webapi.DocumentServiceAPI;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Timofey Teplykh
 */
public class DocumentService implements DocumentServiceAPI {

    @Inject
    FolderRemoteService folderRemoteService;

    @Inject
    FileRemoteService fileRemoteService;

    @Override
    public TreeNode createDocuments() {
        TreeNode root = new DefaultTreeNode(new Document("Хранилище", "-", "Folder"), null);

        buildFSTree("", root);

        return root;
    }

    private void buildFSTree(@Nullable final String folder, @Nullable final TreeNode root) {
        final List<String> subFolders = folderRemoteService.getListFolderNameRoot(folder);
        for (int i = 0; i <= subFolders.size()-1 ; i++) {
            String currentFolder = subFolders.get(i);
            TreeNode folderNode = new DefaultTreeNode(new Document(currentFolder,"-","Folder"),root);
            synchronizeFiles(folder+"/"+currentFolder, folderNode);
            buildFSTree(folder+"/"+currentFolder, folderNode);
        }
    }

    private void synchronizeFiles(@Nullable final String currentFolder,@Nullable final TreeNode node) {
        final List<String> files = fileRemoteService.getListFileNameRoot(currentFolder);
        for (int i = 0; i <= files.size()-1 ; i++) {
            TreeNode fileNode = new DefaultTreeNode("document",new Document(files.get(i),"1","File"),node);
        }
    }

}