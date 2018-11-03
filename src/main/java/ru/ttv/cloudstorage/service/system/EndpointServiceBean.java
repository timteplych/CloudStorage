package ru.ttv.cloudstorage.service.system;

import org.jetbrains.annotations.NotNull;
import ru.ttv.cloudstorage.api.annotation.Loggable;
import ru.ttv.cloudstorage.api.endpoint.*;
import ru.ttv.cloudstorage.api.system.EndpointService;
import ru.ttv.cloudstorage.api.system.SettingService;

import javax.inject.Inject;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Timofey Teplykh
 */
public class EndpointServiceBean implements EndpointService {

    @Inject
    private SettingService settingService;

    @Inject
    private EndpointApplicationAPI endpointApplicationAPI;

    @Inject
    private EndpointCloudAPI endpointCloudAPI;

    @Loggable
    @Override
    public void init() {
        registry(endpointCloudAPI,endpointApplicationAPI);
    }

    private void registry(EndpointAPI... services){
        @NotNull final List<String> result = new ArrayList<>();
        @NotNull final String baseURL = settingService.getSyncEndpoint();
        for (EndpointAPI service: services){
            result.add(registry(baseURL,service));
            info(result);
        }
    }

    @NotNull
    private String registry(@NotNull final String baseURL,@NotNull final EndpointAPI api){
        @NotNull final String serviceURL = baseURL + api.getClass().getSimpleName();
        Endpoint.publish(serviceURL,api);
        return serviceURL;
    }

    private void info(@NotNull final List<String> urls){
        if(urls.isEmpty()) return;
        for (String url: urls){
            System.out.println(url+"?wsdl");
        }
    }

    @Override
    public void start() {

    }

}
