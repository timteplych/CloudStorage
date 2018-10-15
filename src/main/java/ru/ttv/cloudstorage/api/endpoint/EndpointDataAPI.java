package ru.ttv.cloudstorage.api.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * @author Timofey Teplykh
 */
public interface EndpointDataAPI extends EndpointAPI {
    //Folder methods
    @WebMethod
    String getListFolderNameRoot();

    @WebMethod
    void createFolder(@WebParam(name="FolderName")String folderName);

    @WebMethod
    void removeFolder(@WebParam(name="FolderName")String folderName);

    @WebMethod
    void clearRoot();

    //File methods
    @WebMethod
    byte[] readData(@WebParam(name="FileName")String fileName, @WebParam(name="Folder")String folder);

    @WebMethod
    String fileExist(@WebParam(name="FileName")String fileName, @WebParam(name="Folder")String folder);

    @WebMethod
    void removeFile(@WebParam(name="FileName")String fileName);

    @WebMethod
    void createTextFile(@WebParam(name="FileName")String fileName,  @WebParam(name="Folder")String folder, @WebParam(name="Text")String text);

    @WebMethod
    public void writeData(@WebParam(name="FileName")String fileName, @WebParam(name="Folder")String folder, @WebParam(name="DataBytes") byte[] dataBytes);

    @WebMethod
    public String getListFileNameRoot();

    @WebMethod
    public void clearFilesRoot();

}
