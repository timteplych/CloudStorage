package ru.ttv.cloudstorage.service.system;

import lombok.SneakyThrows;
import org.apache.jackrabbit.rmi.repository.URLRemoteRepository;
import org.jboss.weld.environment.se.WeldContainer;
import org.jetbrains.annotations.Nullable;
import ru.ttv.cloudstorage.api.annotation.Loggable;
import ru.ttv.cloudstorage.api.system.ApplicationService;
import ru.ttv.cloudstorage.api.system.SettingService;
import ru.ttv.cloudstorage.api.system.TimerService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class ApplicationServiceBean implements ApplicationService {

    @Inject
    private SettingService settingService;

    @Inject
    private TimerService timerService;

    @Inject
    WeldContainer container;

    private Repository repository = null;

    private Session session = null;

    private Exception error = null;

    @Override
    @Loggable
    public void init() {
        if(settingService.getSyncActive()) timerService.start();
        if(settingService.getJcrActive()) login();
    }

    @Override
    @Loggable
    public boolean login() {
        if(status()) return false;
        try {
            final String jsrURL = settingService.getJcrUrl();
            repository = new URLRemoteRepository(jsrURL);
            final String jsrLogin = settingService.getJcrLogin();
            final String jsrPassword = settingService.getJcrPassword();
            final char[] password = jsrPassword.toCharArray();
            final SimpleCredentials credentials = new SimpleCredentials(jsrLogin,password);
            session = repository.login(credentials);
            return true;
        }catch (Exception e){
            error = e;
            return false;
        }
    }

    @Override
    public boolean status() {
        return repository != null && session != null;
    }

    @Override
    public void shutdown() {
        container.shutdown();
        System.exit(0);
    }


    @Override
    @Loggable
    public boolean logout() {
        if(repository == null) return false;
        if(session == null) return false;
        try {
            session.logout();
            repository = null;
            session = null;
            timerService.stop();
            return true;
        }catch (Exception e){
            error = e;
            return false;
        }
    }


    @Override
    @SneakyThrows
    public boolean save() {
        if(!status()) return false;
        session.save();
        return true;
    }

    @Override
    @Nullable
    public Exception error() {
        return error;
    }

    @Override
    @Nullable
    public Repository repository() {
        return repository;
    }

    @Override
    @Nullable
    public Session session() {
        return session;
    }

    @Override
    @Nullable
    @SneakyThrows
    public Node getRootNode() {
        if(!status()) return null;
        return session.getRootNode();
    }
}
