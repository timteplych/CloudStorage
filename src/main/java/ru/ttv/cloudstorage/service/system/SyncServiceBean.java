package ru.ttv.cloudstorage.service.system;

import ru.ttv.cloudstorage.api.annotation.LocalToRemoteSync;
import ru.ttv.cloudstorage.api.annotation.RemoteToLocalSync;
import ru.ttv.cloudstorage.api.sync.SyncLocalToRemoteEventAPI;
import ru.ttv.cloudstorage.api.system.SyncService;
import ru.ttv.cloudstorage.api.system.TimerService;
import ru.ttv.cloudstorage.service.sync.SyncLocalToRemoteEvent;
import ru.ttv.cloudstorage.service.sync.SyncRemoteToLocalEvent;

import javax.enterprise.event.Event;
import javax.inject.Inject;


/**
 * @author Timofey Teplykh
 */
public class SyncServiceBean implements SyncService {

    @Inject
    private TimerService timerService;

    @Inject @LocalToRemoteSync
    private Event<SyncService> syncLocalToRemoteEvent;

    @Inject @RemoteToLocalSync
    private Event<SyncService> syncRemoteToLocalEvent;

    @Override
    public void sync() {

        syncRemoteToLocalEvent.fire(this);
        syncLocalToRemoteEvent.fire(this);

    }

    @Override
    public boolean status() {
        return timerService.getActive() ;
    }



    @Override
    public boolean start() {
        return timerService.start();
    }

    @Override
    public boolean stop() {
        return timerService.stop();
    }

}
