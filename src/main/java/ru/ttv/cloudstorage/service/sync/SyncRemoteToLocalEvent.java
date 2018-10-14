package ru.ttv.cloudstorage.service.sync;


import ru.ttv.cloudstorage.api.sync.SyncRemoteToLocalEventAPI;

/**
 * @author Timofey Teplykh
 */
public class SyncRemoteToLocalEvent implements SyncRemoteToLocalEventAPI {

    @Override
    public void fire() {
        //System.out.println("remote to local");
    }

}
