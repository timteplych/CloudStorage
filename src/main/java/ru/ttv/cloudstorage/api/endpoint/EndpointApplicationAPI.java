package ru.ttv.cloudstorage.api.endpoint;

import ru.ttv.cloudstorage.dto.ResultDTO;

import javax.jws.WebMethod;

/**
 * @author Timofey Teplykh
 */
public interface EndpointApplicationAPI extends EndpointAPI {

    @WebMethod
    ResultDTO ping();

    @WebMethod
    ResultDTO shutdown();

    @WebMethod
    String connected();

    @WebMethod
    ResultDTO login();

    @WebMethod
    ResultDTO logout();

    @WebMethod
    ResultDTO status();

    @WebMethod
    ResultDTO sync();

    @WebMethod
    ResultDTO start();

    @WebMethod
    ResultDTO stop();

}
