package ru.ttv.cloudstorage.webapp.webapi;

import org.jetbrains.annotations.NotNull;
import org.primefaces.model.TreeNode;
import ru.ttv.cloudstorage.webapp.document.DocumentService;

/**
 * @author Timofey Teplykh
 */
public interface StorageViewAPI {

    void init();

    void setService(DocumentService service);

    @NotNull
    TreeNode getRoot();

    void updateFolderTree();

}