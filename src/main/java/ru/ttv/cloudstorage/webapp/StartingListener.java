package ru.ttv.cloudstorage.webapp;

import ru.ttv.cloudstorage.api.system.BootstrapService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.servlet.ServletContext;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class StartingListener {

    @Inject
    BootstrapService bootstrapService;

    public void init(@Observes @Initialized(ApplicationScoped.class) ServletContext context){
        bootstrapService.init();
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) ServletContext context) {
        // Perform action during application shutdown
    }
}
