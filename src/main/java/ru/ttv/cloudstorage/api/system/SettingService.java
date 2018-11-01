package ru.ttv.cloudstorage.api.system;

import org.jetbrains.annotations.NotNull;
/**
 *@author Timofey Teplykh
 */
public interface SettingService {
    void init();

    void saveProperties();

    @NotNull
    Boolean getJcrActive();

    @NotNull
    String getJcrUrl();

    @NotNull
    String getJcrLogin();

    @NotNull
    String getJcrPassword();

    @NotNull
    String getSyncFolder();

    @NotNull
    Integer getSyncTimeout();

    @NotNull
    String getSyncEndpoint();

    @NotNull
    Boolean getSyncActive();

    @NotNull
    String getSqlitePath();




    void setJcrActive(Boolean jcrActive);

    void setJcrUrl(String jcrUrl);

    void setJcrLogin(String jcrLogin);

    void setJcrPassword(String jcrPassword);

    void setSyncFolder(String syncFolder);

    void setSyncTimeout(Integer syncTimeout);

    void setSyncEndpoint(String syncEndpoint);

    void setSyncActive(Boolean syncActive);

    void setSqlitePath(String sqlitePath);
}