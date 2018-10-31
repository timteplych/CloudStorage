package ru.ttv.cloudstorage.service.system;

import ru.ttv.cloudstorage.api.annotation.Loggable;
import ru.ttv.cloudstorage.api.db.DBConnectionAPI;
import ru.ttv.cloudstorage.api.db.DBProcessingAPI;
import ru.ttv.cloudstorage.api.local.FolderLocalService;
import ru.ttv.cloudstorage.api.system.ApplicationService;
import ru.ttv.cloudstorage.api.system.BootstrapService;
import ru.ttv.cloudstorage.api.system.EndpointService;
import ru.ttv.cloudstorage.api.system.SettingService;
import ru.ttv.cloudstorage.service.db.DBConnection;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class BootStrapServiceBean implements BootstrapService {

    @Inject
    private ApplicationService applicationService;

    @Inject
    private SettingService settingService;

    @Inject
    private EndpointService endpointService;

    @Inject
    private FolderLocalService folderLocalservice;

    @Inject
    private DBConnectionAPI dbConnection;

    @Inject
    private DBProcessingAPI dbProcessing;

    @Override
    @Loggable
    public void init() {
        settingService.init();
        dbConnection.init();
        dbProcessing.initDB();
        //endpointService.init();
        folderLocalservice.init();
        applicationService.init();
    }
}
