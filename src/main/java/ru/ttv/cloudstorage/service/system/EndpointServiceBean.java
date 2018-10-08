package ru.ttv.cloudstorage.service.system;

import ru.ttv.cloudstorage.api.system.EndpointService;
import ru.ttv.cloudstorage.api.system.SettingService;

import javax.inject.Inject;

/**
 * @author Timofey Teplykh
 */
public class EndpointServiceBean implements EndpointService {

    @Inject
    private SettingService settingService;

    @Override
    public void init() {

    }

    @Override
    public void start() {

    }
}
