package ru.ttv.cloudstorage.service.system;

import ru.ttv.cloudstorage.api.system.SyncService;
import ru.ttv.cloudstorage.api.system.TimerService;

import javax.inject.Inject;


/**
 * @author Timofey Teplykh
 */
public class SyncServiceBean implements SyncService {

    @Inject
    private TimerService timerService;

    @Inject
    private Event<SyncRemoteToLocalEvent> syncRemoteToLocalEvent;

    @Override
    public boolean status() {
        return false;
    }

    @Override
    public void sync() {

    }

    @Override
    public boolean start() {
        return false;
    }

    @Override
    public boolean stop() {
        return false;
    }
}
