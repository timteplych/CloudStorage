package ru.ttv.cloudstorage.endpoint;

import ru.ttv.cloudstorage.api.endpoint.EndpointApplicationAPI;
import ru.ttv.cloudstorage.api.system.ApplicationService;
import ru.ttv.cloudstorage.api.system.SyncService;
import ru.ttv.cloudstorage.dto.ResultDTO;
import ru.ttv.cloudstorage.dto.SuccesDTO;

import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author Timofey Teplykh
 */
@WebService
public class EndpointApplication implements EndpointApplicationAPI {

    @Inject
    private ApplicationService applicationService;

    @Inject
    private SyncService syncService;

    @Override
    @WebMethod
    public ResultDTO ping(){
        return new SuccesDTO();
    }

    @Override
    @WebMethod
    public ResultDTO shutdown(){
        applicationService.shutdown();
        return new SuccesDTO();
    }

    @Override
    @WebMethod
    public String connected(){
        return (new ResultDTO(applicationService.status())).toString();
    }

    @Override
    @WebMethod
    public ResultDTO login(){
        return new ResultDTO(applicationService.login());
    }

    @Override
    @WebMethod
    public ResultDTO logout(){
        return new ResultDTO(applicationService.logout());
    }

    @Override
    @WebMethod
    public ResultDTO status(){
        return new ResultDTO(syncService.status());
    }

    @Override
    @WebMethod
    public ResultDTO sync(){
        syncService.sync();
        return new SuccesDTO();
    }

    @Override
    @WebMethod
    public ResultDTO start(){
        return new ResultDTO(syncService.start());
    }

    @Override
    @WebMethod
    public ResultDTO stop(){
        return new ResultDTO(syncService.stop());
    }



}
