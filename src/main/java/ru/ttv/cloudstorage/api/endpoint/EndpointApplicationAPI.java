package ru.ttv.cloudstorage.api.endpoint;


import javax.jws.WebMethod;

/**
 * @author Timofey Teplykh
 */
public interface EndpointApplicationAPI extends EndpointAPI {

    @WebMethod
    String ping();

    @WebMethod
    String shutdown();

    @WebMethod
    String connected();

    @WebMethod
    String login();

    @WebMethod
    String logout();

    @WebMethod
    String status();

    @WebMethod
    String sync();

    @WebMethod
    String start();

    @WebMethod
    String stop();

}
