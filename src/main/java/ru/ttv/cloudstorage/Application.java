package ru.ttv.cloudstorage;

import ru.ttv.cloudstorage.service.system.BootStrapServiceBean;

import javax.enterprise.inject.se.SeContainerInitializer;

/**
 * @author Timofey Teplykh
 *
 */
public class Application
{
    public static void main( String[] args )
    {
        SeContainerInitializer.newInstance().addPackages(Application.class).initialize()
                .select(BootStrapServiceBean.class).get().init();
    }
}
