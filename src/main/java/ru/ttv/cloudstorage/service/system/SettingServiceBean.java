package ru.ttv.cloudstorage.service.system;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import ru.ttv.cloudstorage.api.annotation.Loggable;
import ru.ttv.cloudstorage.api.system.SettingService;

import javax.enterprise.context.ApplicationScoped;
import java.io.*;
import java.util.Properties;

/**
 * @author Timofey Teplykh
 */
@Getter
@Setter
@ApplicationScoped
public class SettingServiceBean implements SettingService {

    private static final String FILE_NAME = "application.properties";
    private static final String CUSTOM_FILE_PATH = "../application_custom.properties";
    private static final String KEY_JCR_URL = "jcr.url";
    private static final String KEY_JCR_LOGIN = "jcr.login";
    private static final String KEY_JCR_PASSWORD = "jcr.password";
    private static final String KEY_SYNC_FOLDER = "sync.folder";
    private static final String KEY_SYNC_TIMEOUT = "sync.timeout";
    private static final String KEY_SYNC_ENDPOINT = "sync.endpoint";
    private static final String KEY_SYNC_ACTIVE = "sync.active";
    private static final String KEY_JCR_ACTIVE = "jcr.active";

    private static final String KEY_SQLITE_PATH = "sqlite.path";

    private String jcrUrl;
    private String jcrLogin;
    private String jcrPassword;
    private Boolean jcrActive;
    private String syncFolder;
    private Integer syncTimeout;
    private String syncEndpoint;
    private Boolean syncActive;
    private String sqlitePath;


    @Override
    @Loggable
    @SneakyThrows
    public void init() {
        final ClassLoader classLoader = SettingServiceBean.class.getClassLoader();
        InputStream inputStream = null;
        final File file = new File(CUSTOM_FILE_PATH);
        if(file.exists()){
            inputStream = new FileInputStream(CUSTOM_FILE_PATH);
        }
        else inputStream = classLoader.getResourceAsStream(FILE_NAME);
        final Properties properties = new Properties();
        properties.load(inputStream);
        jcrUrl = properties.getOrDefault(KEY_JCR_URL,"localhost").toString();
        jcrLogin = properties.getOrDefault(KEY_JCR_LOGIN,"admin").toString();
        jcrPassword = properties.getOrDefault(KEY_JCR_PASSWORD,"admin").toString();
        jcrActive = Boolean.parseBoolean(properties.getOrDefault(KEY_JCR_ACTIVE,"true").toString());
        syncFolder = properties.getOrDefault(KEY_SYNC_FOLDER,"./temp/").toString();
        syncTimeout = Integer.parseInt(properties.getOrDefault(KEY_SYNC_TIMEOUT,"1000").toString());
        syncActive = Boolean.parseBoolean(properties.getOrDefault(KEY_SYNC_ACTIVE,"true").toString());
        syncEndpoint = properties.getOrDefault(KEY_SYNC_ENDPOINT,"http://localhost:8181/").toString();
        sqlitePath = properties.getOrDefault(KEY_SQLITE_PATH,"../cloudstorage.db").toString();

    }

    public void saveProperties(){
        Properties properties = new Properties();
        OutputStream output = null;
        try {

            output = new FileOutputStream(CUSTOM_FILE_PATH);

            // set the properties value
            properties.setProperty(KEY_JCR_URL, jcrUrl);
            properties.setProperty(KEY_JCR_LOGIN, jcrLogin);
            properties.setProperty(KEY_JCR_PASSWORD, jcrPassword);
            properties.setProperty(KEY_JCR_ACTIVE, Boolean.toString(jcrActive));
            properties.setProperty(KEY_SYNC_FOLDER, syncFolder);
            properties.setProperty(KEY_SYNC_TIMEOUT, Integer.toString(syncTimeout));
            properties.setProperty(KEY_SYNC_ENDPOINT, syncEndpoint);
            properties.setProperty(KEY_SYNC_ACTIVE, Boolean.toString(syncActive));
            properties.setProperty(KEY_SQLITE_PATH, sqlitePath);

            properties.store(output, null);

        } catch (IOException io) {
            io.printStackTrace();
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}