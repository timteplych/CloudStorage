package ru.ttv.cloudstorage.handler.keyboard;

import ru.ttv.cloudstorage.event.keyboard.KeyboardCommandEvent;
import ru.ttv.cloudstorage.event.keyboard.KeyboardInitEvent;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;


/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class KeyboardInitHandler {

    @Inject
    private Event<KeyboardCommandEvent> keyboardInputCommandEvent;

    public void observe(@Observes final KeyboardInitEvent event){
        System.out.println();
        System.out.println("------WELCOME TO COMMANDLINE INTERFACE------");
        System.out.println("------ENTER \"help\" FOR LIST OF COMMANDS------");
        keyboardInputCommandEvent.fire(new KeyboardCommandEvent());
    }
}
