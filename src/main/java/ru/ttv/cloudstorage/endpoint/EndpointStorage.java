package ru.ttv.cloudstorage.endpoint;

import ru.ttv.cloudstorage.api.endpoint.EndpointStorageAPI;
import ru.ttv.cloudstorage.api.local.FileLocalService;
import ru.ttv.cloudstorage.api.local.FolderLocalService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Timofey Teplykh
 */
@WebService
public class EndpointStorage implements EndpointStorageAPI {

    @Inject
    public FolderLocalService folderLocalService;

    @Inject
    public FileLocalService fileLocalService;

    @Override
    @WebMethod
    public void init() {
        folderLocalService.init();
    }

    @Override
    @WebMethod
    public String getListFolderNameRoot() {
        return folderLocalService.getListFolderNameRoot("").toString();
    }

    @Override
    @WebMethod
    public void createFolder(@WebParam(name="FolderName")String folderName) {
        folderLocalService.createFolder(folderName);
    }

    @Override
    @WebMethod
    public void removeFolder(@WebParam(name="FolderName")String folderName) {
        folderLocalService.removeFolder(folderName);
    }

    @Override
    @WebMethod
    public void clearRoot() {
        folderLocalService.clearRoot();
    }

    @Override
    @WebMethod
    public byte[] readData(@WebParam(name="FileName")String fileName, @WebParam(name="Folder")String folder) {
        return fileLocalService.readData(fileName, folder);
    }

    @Override
    @WebMethod
    public String fileExist(@WebParam(name="FileName")String fileName, @WebParam(name="Folder")String folder) {
        return Boolean.toString(fileLocalService.exist(fileName,folder));
    }

    @Override
    @WebMethod
    public void removeFile(@WebParam(name="FileName")String fileName) {
        fileLocalService.remove(fileName);
    }

    @Override
    @WebMethod
    public void writeData(@WebParam(name="FileName")String fileName,  @WebParam(name="Folder")String folder, @WebParam(name="DataBytes") byte[] dataBytes) {
        fileLocalService.writeData(fileName, folder, dataBytes);
    }

    @Override
    public void createTextFile(@WebParam(name="FileName")String fileName, @WebParam(name="Folder")String folder, @WebParam(name="Text")String text) {
        fileLocalService.createTextFile(fileName, folder, text);
    }

    @Override
    @WebMethod
    public String getListFileNameRoot() {
        return fileLocalService.getListFileNameRoot("").toString();
    }

    @Override
    public void clearFilesRoot() {
        fileLocalService.clearRoot();
    }
}
