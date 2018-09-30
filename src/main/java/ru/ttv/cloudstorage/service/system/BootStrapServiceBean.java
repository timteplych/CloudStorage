package ru.ttv.cloudstorage.service.system;

import ru.ttv.cloudstorage.api.annotation.Loggable;
import ru.ttv.cloudstorage.api.local.FolderLocalservice;
import ru.ttv.cloudstorage.api.system.ApplicationService;
import ru.ttv.cloudstorage.api.system.BootstrapService;
import ru.ttv.cloudstorage.api.system.EndpointService;
import ru.ttv.cloudstorage.api.system.SettingService;

import javax.inject.Inject;

/**
 * @author Timofey Teplykh
 */
public class BootStrapServiceBean implements BootstrapService {

    @Inject
    private ApplicationService applicationService;

    @Inject
    private SettingService settingService;

    @Inject
    EndpointService endpointService;

    @Inject
    FolderLocalservice folderLocalservice;

    @Override
    @Loggable
    public void init() {
        settingService.init();
        endpointService.init();
        folderLocalservice.init();
        applicationService.init();
    }
}
