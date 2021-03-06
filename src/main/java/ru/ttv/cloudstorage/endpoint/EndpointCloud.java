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
@WebService(serviceName = "ws/EndpointCloud")
public class EndpointCloud implements EndpointCloudAPI {

    @Inject
    public FolderRemoteService folderRemoteService;

    @Inject
    public FileRemoteService fileRemoteService;

    @Override
    @WebMethod
    public String getListFolderNameRoot() {
        List<String> folderList = folderRemoteService.getListFolderNameRoot("");
        return folderList.toString();
    }

    @Override
    @WebMethod
    public void createFolder(@WebParam(name="FolderName")String folderName) {
        folderRemoteService.createFolder(folderName);
    }

    @Override
    @WebMethod
    public void removeFolder(@WebParam(name="FolderName")String folderName) {
        folderRemoteService.removeFolder(folderName);
    }

    @Override
    @WebMethod
    public void clearRoot() {
        folderRemoteService.clearRoot();
    }

    @Override
    @WebMethod
    public byte[] readData(@WebParam(name="FileName")String fileName,  @WebParam(name="Folder")String folder) {
        return fileRemoteService.readData(fileName, folder);
    }

    @Override
    @WebMethod
    public String fileExist(@WebParam(name="FileName")String fileName,  @WebParam(name="Folder")String folder) {
        return Boolean.toString(fileRemoteService.exist(fileName,folder));
    }

    @Override
    @WebMethod
    public void removeFile(@WebParam(name="FileName")String fileName) {
        fileRemoteService.remove(fileName);
    }

    @Override
    public void writeData(String fileName, String folder, byte[] dataBytes) {
        fileRemoteService.writeData(fileName, folder, dataBytes);
    }

    @Override
    public void createTextFile(@WebParam(name="FileName")String fileName, @WebParam(name="Folder")String folder, @WebParam(name="Text")String text) {
        fileRemoteService.createTextFile(fileName, folder, text);
    }

    @Override
    public String getListFileNameRoot() {
        return fileRemoteService.getListFileNameRoot("").toString();
    }

    @Override
    public void clearFilesRoot() {
        fileRemoteService.clearRoot();
    }

}
