package ru.ttv.cloudstorage.webapp.webbean;

import lombok.Getter;
import ru.ttv.cloudstorage.api.system.SettingService;
import ru.ttv.cloudstorage.webapp.webapi.SettingControlAPI;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * @author Timofey Teplykh
 */
@Getter
@ManagedBean
@ApplicationScoped
public class SettingControlBean implements SettingControlAPI {

    @Inject
    private SettingService settingService;

    private boolean jcrActive;
    private String jcrURL;
    private String jcrLogin;
    private String jcrPassword;

    private boolean syncActive;
    private int timeout;
    private String syncFolder;

    @PostConstruct
    public void init(){
        jcrActive = settingService.getJcrActive();
        jcrURL = settingService.getJcrUrl();
        jcrLogin = settingService.getJcrLogin();
        jcrPassword = settingService.getJcrPassword();
        syncActive = settingService.getSyncActive();
        timeout = settingService.getSyncTimeout();
        syncFolder = settingService.getSyncFolder();
    }

    public void setJcrActive(boolean jcrActive) {
        this.jcrActive = jcrActive;
        settingService.setJcrActive(jcrActive);
    }

    public void setJcrURL(String jcrURL) {
        this.jcrURL = jcrURL;
        settingService.setJcrUrl(jcrURL);
    }

    public void setJcrLogin(String jcrLogin) {
        this.jcrLogin = jcrLogin;
        settingService.setJcrLogin(jcrLogin);
    }

    public void setJcrPassword(String jcrPassword) {
        this.jcrPassword = jcrPassword;
        settingService.setJcrPassword(jcrPassword);
    }

    public void setSyncActive(boolean syncActive) {
        this.syncActive = syncActive;
        settingService.setSyncActive(syncActive);
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
        settingService.setSyncTimeout(timeout);
    }

    public void setSyncFolder(String syncFolder) {
        this.syncFolder = syncFolder;
        settingService.setSyncFolder(syncFolder);
    }

    public void saveProperties(){
        settingService.saveProperties();
        addMessage("Настройки сохранены!");
    }

    private void addMessage(String summary) {
        FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }



}
