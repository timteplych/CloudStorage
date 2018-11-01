package ru.ttv.cloudstorage.webapp.webapi;

import org.primefaces.model.TreeNode;
import ru.ttv.cloudstorage.webapp.document.DocumentService;

/**
 * @author Timofey Teplykh
 */
public interface StorageViewAPI {

    public void init();

    public void setService(DocumentService service);

    public TreeNode getRoot();

    public void updateFolderTree();

}
