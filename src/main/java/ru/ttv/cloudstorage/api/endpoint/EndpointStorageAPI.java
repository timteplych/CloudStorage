package ru.ttv.cloudstorage.api.endpoint;

import javax.jws.WebMethod;

/**
 * @author Timofey Teplykh
 */
public interface EndpointStorageAPI extends EndpointDataAPI {

    @WebMethod
    public void init();

}
