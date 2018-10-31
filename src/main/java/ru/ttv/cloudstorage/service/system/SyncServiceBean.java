package ru.ttv.cloudstorage.service.system;

import ru.ttv.cloudstorage.api.system.SyncService;
import ru.ttv.cloudstorage.api.system.TimerService;
import ru.ttv.cloudstorage.service.db.DBProcessing;
import ru.ttv.cloudstorage.service.sync.SyncLocalToRemoteEvent;
import ru.ttv.cloudstorage.service.sync.SyncRemoteToLocalEvent;

import javax.inject.Inject;
import java.sql.SQLException;


/**
 * @author Timofey Teplykh
 */
public class SyncServiceBean implements SyncService {

    @Inject
    private TimerService timerService;

    @Inject
    private SyncRemoteToLocalEvent syncRemoteToLocalEvent;

    @Inject
    private SyncLocalToRemoteEvent syncLocalToRemoteEvent;

    @Inject
    private DBProcessing dbProcessing;

    @Override
    public void sync() {
//        try {
//            if(dbProcessing.isDbEmpty()){
//
//            }else{
//                System.out.println("DB IS NOT EMPTY!!!");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        syncLocalToRemoteEvent.fire();
        syncRemoteToLocalEvent.fire();
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
