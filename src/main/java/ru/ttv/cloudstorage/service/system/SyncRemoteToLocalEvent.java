package ru.ttv.cloudstorage.service.system;


import ru.ttv.cloudstorage.api.sync.SyncRemoteToLocalEventAPI;

/**
 * @author Timofey Teplykh
 */
public class SyncRemoteToLocalEvent implements SyncRemoteToLocalEventAPI {

    @Override
    public void fire() {
        System.out.println("remote to local");
    }

}
