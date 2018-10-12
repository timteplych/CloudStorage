package ru.ttv.cloudstorage.service.system;

import ru.ttv.cloudstorage.api.system.SyncService;
import ru.ttv.cloudstorage.api.system.TimerService;

import javax.enterprise.event.Event;
import javax.inject.Inject;


/**
 * @author Timofey Teplykh
 */
public class SyncServiceBean implements SyncService {

    @Inject
    private TimerService timerService;

    @Inject
    private Event<SyncRemoteToLocalEvent> syncRemoteToLocalEvent;

    @Inject
    private Event<SyncLocalToRemoteEvent> syncLocalToRemoteEvent;

    @Override
    public void sync() {

        syncRemoteToLocalEvent.fire(new SyncRemoteToLocalEvent());
        syncLocalToRemoteEvent.fire(new SyncLocalToRemoteEvent());
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
