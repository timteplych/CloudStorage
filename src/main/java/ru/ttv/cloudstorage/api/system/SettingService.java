package ru.ttv.cloudstorage.api.system;

import org.jetbrains.annotations.NotNull;
/**
 *@author Timofey Teplykh
 */
public interface SettingService {
    void init();

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
}
