package ru.ttv.cloudstorage.api.sync;

import ru.ttv.cloudstorage.api.system.SyncService;

/**
 * @author Timofey Teplykh
 */
public interface SyncEvent {
    void doSynchronize(SyncService syncServiceBean);
}
