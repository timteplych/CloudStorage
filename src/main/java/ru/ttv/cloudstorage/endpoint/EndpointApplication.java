package ru.ttv.cloudstorage.endpoint;

import ru.ttv.cloudstorage.api.endpoint.EndpointApplicationAPI;
import ru.ttv.cloudstorage.api.system.ApplicationService;
import ru.ttv.cloudstorage.api.system.SyncService;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @author Timofey Teplykh
 */
@WebService
public class EndpointApplication implements EndpointApplicationAPI {

    private static final String STATUS_SUCCESS = "success";

    @Inject
    private ApplicationService applicationService;

    @Inject
    private SyncService syncService;

    @Override
    @WebMethod
    public String ping(){
        return STATUS_SUCCESS;
    }

    @Override
    @WebMethod
    public String shutdown(){
        applicationService.shutdown();
        return STATUS_SUCCESS;
    }

    @Override
    @WebMethod
    public String connected(){
        return (Boolean.toString(applicationService.status()));
    }

    @Override
    @WebMethod
    public String login(){
        return Boolean.toString(applicationService.login());
    }

    @Override
    @WebMethod
    public String logout(){
        return Boolean.toString(applicationService.logout());
    }

    @Override
    @WebMethod
    public String status(){
        return Boolean.toString(syncService.status());
    }

    @Override
    @WebMethod
    public String sync(){
        syncService.sync();
        return STATUS_SUCCESS;
    }

    @Override
    @WebMethod
    public String start(){
        return Boolean.toString(syncService.start());
    }

    @Override
    @WebMethod
    public String stop(){
        return Boolean.toString(syncService.stop());
    }

}
