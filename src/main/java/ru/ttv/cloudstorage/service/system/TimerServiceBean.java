package ru.ttv.cloudstorage.service.system;

import ru.ttv.cloudstorage.api.annotation.Loggable;
import ru.ttv.cloudstorage.api.system.SettingService;
import ru.ttv.cloudstorage.api.system.SyncTask;
import ru.ttv.cloudstorage.api.system.TimerService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import javax.inject.Inject;
import java.util.Timer;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class TimerServiceBean implements TimerService {

    @Inject
    private SettingService settingService;

    private final Timer timer = new Timer();

    private SyncTask task = null;

    public boolean getActive(){
        return task != null;
    }

    public void setActive(boolean active){
        if(active) start(); else stop();
    }

    @Loggable
    @Override
    public synchronized boolean start(){
        if(task!=null) return false;
        final Integer timeout = settingService.getSyncTimeout();
        task = CDI.current().select(SyncTask.class).get();
        timer.schedule(task.get(),10000,timeout);
        return true;
    }

    @Loggable
    @Override
    public synchronized boolean stop(){
        if(task == null){
            return false;
        }
        task.cancel();
        task = null;
        return true;
    }

    @Override
    public void restart(){
        stop();
        start();
    }
}
