package ru.ttv.cloudstorage.webapp.webbean;


import ru.ttv.cloudstorage.api.system.ApplicationService;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Inject;

/**
 * @author Timofey Teplykh
 */
@ManagedBean
@ApplicationScoped
public class CloudControllerBean {
    private static final String STATUS_ACTIVE = "Приложение запущено";
    private static final String STATUS_STOPPED = "Приложение остановлено";

    @Inject
    private ApplicationService applicationService;

    public String getStatus(){
        if(applicationService.status()){
            return STATUS_ACTIVE;
        }else{
            return STATUS_STOPPED;
        }
    }

    public void buttonToggleAction(){
        if(applicationService.status()){
            applicationService.logout();
        }else{
            applicationService.init();
        }

    }
}
