package ru.ttv.cloudstorage.webapp.webbean;

import org.primefaces.model.TreeNode;
import ru.ttv.cloudstorage.webapp.document.DocumentService;
import ru.ttv.cloudstorage.webapp.webapi.StorageViewAPI;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 * @author Timofey Teplykh
 */
@ManagedBean
public class StorageViewBean implements StorageViewAPI {

    @Inject
    private DocumentService service;

    private TreeNode root;

    @Override
    @PostConstruct
    public void init() {
        update();
    }

    @Override
    public void setService(DocumentService service) {

    }

    @Override
    public TreeNode getRoot() {
        return root;
    }

    @Override
    public void updateFolderTree() {
        update();
    }

    private void update(){
        root = service.createDocuments();
    }
}