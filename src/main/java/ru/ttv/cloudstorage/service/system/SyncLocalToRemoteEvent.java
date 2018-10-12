package ru.ttv.cloudstorage.service.system;


import javax.enterprise.event.Event;
import javax.enterprise.event.NotificationOptions;
import javax.enterprise.util.TypeLiteral;
import java.lang.annotation.Annotation;
import java.util.concurrent.CompletionStage;

/**
 * @author Timofey Teplykh
 */
public class SyncLocalToRemoteEvent implements Event {
    @Override
    public void fire(Object o) {

    }

    @Override
    public CompletionStage fireAsync(Object o) {
        return null;
    }

    @Override
    public CompletionStage fireAsync(Object o, NotificationOptions notificationOptions) {
        return null;
    }

    @Override
    public Event select(Annotation... annotations) {
        return null;
    }

    @Override
    public Event select(Class aClass, Annotation... annotations) {
        return null;
    }

    @Override
    public Event select(TypeLiteral typeLiteral, Annotation... annotations) {
        return null;
    }
}
