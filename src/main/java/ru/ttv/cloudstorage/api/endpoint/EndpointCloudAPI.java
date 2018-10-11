package ru.ttv.cloudstorage.api.endpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * @author Timofey Teplykh
 */
public interface EndpointCloudAPI extends EndpointAPI {

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
    byte[] readData(@WebParam(name="FileName")String fileName);

    @WebMethod
    String exist(@WebParam(name="FileName")String fileName);

    @WebMethod
    void remove(@WebParam(name="FileName")String fileName);

    @WebMethod
    public void writeData(@WebParam(name="FileName")String fileName, @WebParam(name="DataBytes") byte[] dataBytes);

    @WebMethod
    public String getListFileNameRoot();

    @WebMethod
    public void clearFilesRoot();
}
