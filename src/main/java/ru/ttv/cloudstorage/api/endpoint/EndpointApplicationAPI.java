package ru.ttv.cloudstorage.api.endpoint;

import ru.ttv.cloudstorage.dto.ResultDTO;

import javax.jws.WebMethod;

/**
 * @author Timofey Teplykh
 */
public interface EndpointApplicationAPI extends EndpointAPI {

    @WebMethod
    public ResultDTO ping();

    @WebMethod
    public ResultDTO shutdown();

    @WebMethod
    public ResultDTO connected();

    @WebMethod
    public ResultDTO login();

    @WebMethod
    public ResultDTO logout();

    @WebMethod
    public ResultDTO status();

    @WebMethod
    public ResultDTO sync();

    @WebMethod
    public ResultDTO start();

    @WebMethod
    public ResultDTO stop();

}
