package ru.ttv.cloudstorage.api.system;

import org.jetbrains.annotations.Nullable;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.Session;

/**
 * @author Timofey Teplykh
 */
public interface ApplicationService {
    void init();

    void shutdown();

    boolean login();

    boolean logout();

    boolean status();

    boolean save();

    @Nullable
    Exception error();

    @Nullable
    Repository repository();

    @Nullable
    Session session();

    @Nullable
    Node getRootNode();
}
