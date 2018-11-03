package ru.ttv.cloudstorage.api.system;

/**
 * @author Timofey Teplykh
 */
public interface TimerService {

    boolean getActive();

    void setActive(boolean active);

    boolean start();

    boolean stop();

    void restart();

}

