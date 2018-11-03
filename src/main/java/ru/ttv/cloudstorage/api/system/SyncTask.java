package ru.ttv.cloudstorage.api.system;

import org.jetbrains.annotations.NotNull;

import java.util.TimerTask;

/**
 * @author Timofey Teplykh
 */
public interface SyncTask extends Runnable {

    @NotNull
    TimerTask get();

    boolean cancel();

}
