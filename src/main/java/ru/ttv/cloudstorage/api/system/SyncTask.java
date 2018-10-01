package ru.ttv.cloudstorage.api.system;

import java.util.TimerTask;

/**
 * @author Timofey Teplykh
 */
public interface SyncTask extends Runnable {

    TimerTask get();

    boolean cancel();
}
