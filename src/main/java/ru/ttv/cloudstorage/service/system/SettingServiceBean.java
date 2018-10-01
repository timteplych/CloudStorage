package ru.ttv.cloudstorage.service.system;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import ru.ttv.cloudstorage.api.annotation.Loggable;
import ru.ttv.cloudstorage.api.system.SettingService;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Timofey Teplykh
 */
@Getter
@Setter
@ApplicationScoped
public class SettingServiceBean implements SettingService {

    private static final String FILE_NAME = "application.properties";
    private static final String KEY_JCR_URL = "jcr.url";
    private static final String KEY_JCR_LOGIN = "jcr.login";
    private static final String KEY_JCR_PASSWORD = "jcr.password";
    private static final String KEY_SYNC_FOLDER = "sync.folder";
    private static final String KEY_SYNC_TIMEOUT = "sync.timeout";
    private static final String KEY_SYNC_ENDPOINT = "sync.endpoint";
    private static final String KEY_SYNC_ACTIVE = "sync.active";
    private static final String KEY_JCR_ACTIVE = "jcr.active";

    private String jcrUrl;
    private String jcrLogin;
    private String jcrPassword;
    private Boolean jcrActive;
    private String syncFolder;
    private Integer syncTimeout;
    private String syncEndpoint;
    private Boolean syncActive;


    @Override
    @Loggable
    @SneakyThrows
    public void init() {
        final Properties properties = new Properties();
        final ClassLoader classLoader = SettingServiceBean.class.getClassLoader();
        final InputStream inputStream = classLoader.getResourceAsStream(FILE_NAME);
        properties.load(inputStream);
        jcrUrl = properties.getOrDefault(KEY_JCR_URL,"localhost").toString();
        jcrLogin = properties.getOrDefault(KEY_JCR_LOGIN,"admin").toString();
        jcrPassword = properties.getOrDefault(KEY_JCR_PASSWORD,"admin").toString();
        jcrActive = Boolean.parseBoolean(properties.getOrDefault(KEY_JCR_ACTIVE,"true").toString());
        syncFolder = properties.getOrDefault(KEY_SYNC_FOLDER,"./temp/").toString();
        syncTimeout = Integer.parseInt(properties.getOrDefault(KEY_SYNC_TIMEOUT,"1000").toString());
        syncActive = Boolean.parseBoolean(properties.getOrDefault(KEY_SYNC_ACTIVE,"true").toString());
        syncEndpoint = properties.getOrDefault(KEY_SYNC_ENDPOINT,"http://localhost:8181/").toString();

    }
}
