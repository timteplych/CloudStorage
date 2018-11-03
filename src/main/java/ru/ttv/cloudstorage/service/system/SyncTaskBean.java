package ru.ttv.cloudstorage.service.system;

import org.jetbrains.annotations.NotNull;
import ru.ttv.cloudstorage.api.system.SyncService;
import ru.ttv.cloudstorage.api.system.SyncTask;

import javax.inject.Inject;
import java.util.TimerTask;

/**
 * @author Timofey Teplykh
 */
public class SyncTaskBean extends TimerTask implements SyncTask {

    @Inject
    private SyncService syncService;

    @Override
    public boolean cancel(){
        return super.cancel();
    }

    @NotNull
    @Override
    public TimerTask get(){
        return this;
    }

    @Override
    public void run(){
        syncService.sync();
    }

}
