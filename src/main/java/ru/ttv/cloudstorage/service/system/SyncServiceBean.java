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
    private Event<SyncRemoteToLocalEvent> syncRemoteToLocalEventEvent
}
