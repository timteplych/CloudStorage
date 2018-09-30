package ru.ttv.cloudstorage.api.system;

/**
 * @author Timofey Teplykh
 */
public interface SyncService {
    boolean status();

    void sync();

    boolean start();

    boolean stop();
}
