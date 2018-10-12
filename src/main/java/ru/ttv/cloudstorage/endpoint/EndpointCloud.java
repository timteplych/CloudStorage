package ru.ttv.cloudstorage.endpoint;

import ru.ttv.cloudstorage.api.endpoint.EndpointCloudAPI;
import ru.ttv.cloudstorage.api.remote.FileRemoteService;
import ru.ttv.cloudstorage.api.remote.FolderRemoteService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * @author Timofey Teplykh
 */
@WebService
public class EndpointCloud implements EndpointCloudAPI {

    @Inject
    public FolderRemoteService folderRemoteService;

    @Inject
    public FileRemoteService fileRemoteService;

    @Override
    @WebMethod
    public String getListFolderNameRoot() {
        List<String> folderList = folderRemoteService.getListFolderNameRoot();
        return folderList.toString();
    }

    @Override
    @WebMethod
    public void createFolder(@WebParam(name="FolderName")String folderName) {
        folderRemoteService.createFolder(folderName);
    }

    @Override
    @WebMethod
    public void removeFolder(String folderName) {
        folderRemoteService.removeFolder(folderName);
    }

    @Override
    @WebMethod
    public void clearRoot() {
        folderRemoteService.clearRoot();
    }

    @Override
    @WebMethod
    public byte[] readData(@WebParam(name="FileName")String fileName) {
        return fileRemoteService.readData(fileName);
    }

    @Override
    @WebMethod
    public String fileExist(@WebParam(name="FileName")String fileName) {
        return Boolean.toString(fileRemoteService.exist(fileName));
    }

    @Override
    @WebMethod
    public void removeFile(@WebParam(name="FileName")String fileName) {
        fileRemoteService.remove(fileName);
    }

    @Override
    public void writeData(String fileName, byte[] dataBytes) {
        fileRemoteService.writeData(fileName,dataBytes);
    }

    @Override
    public String getListFileNameRoot() {
        return fileRemoteService.getListFileNameRoot().toString();
    }

    @Override
    public void clearFilesRoot() {
        fileRemoteService.clearRoot();
    }
}
