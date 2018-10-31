package ru.ttv.cloudstorage.webapp.webbean;


import ru.ttv.cloudstorage.api.system.ApplicationService;
import ru.ttv.cloudstorage.webapp.webapi.CloudControllerAPI;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * @author Timofey Teplykh
 */
@ManagedBean
@ApplicationScoped
public class CloudControllerBean implements CloudControllerAPI {
    private static final String STATUS_ACTIVE = "Приложение запущено";
    private static final String STATUS_STOPPED = "Приложение остановлено";
    private String status = STATUS_STOPPED;

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
            status = STATUS_STOPPED;
            addMessage(STATUS_STOPPED);
        }else{
            applicationService.init();
            status = STATUS_ACTIVE;
            addMessage(STATUS_ACTIVE);
        }

    }

    private void addMessage(String summary) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
