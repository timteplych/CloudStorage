package ru.ttv.cloudstorage.service.sync;


import ru.ttv.cloudstorage.api.sync.SyncLocalToRemoteEventAPI;

import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.enterprise.util.TypeLiteral;
import java.lang.annotation.Annotation;
import java.util.concurrent.CompletionStage;

/**
 * @author Timofey Teplykh
 */
public class SyncLocalToRemoteEvent implements SyncLocalToRemoteEventAPI {

    @Override
    public void fire() {
        System.out.println("local to remote");
    }
}
