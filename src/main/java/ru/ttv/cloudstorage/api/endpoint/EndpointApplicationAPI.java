package ru.ttv.cloudstorage.api.endpoint;

import ru.ttv.cloudstorage.dto.ResultDTO;

import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 * @author Timofey Teplykh
 */
public interface EndpointApplicationAPI extends EndpointAPI {

    @WebMethod
    public ResultDTO ping();

    @WebMethod
    public ResultDTO shutdown();

    @WebMethod
    public String connected(@WebParam( name = "EmpDetail") String msg);

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
