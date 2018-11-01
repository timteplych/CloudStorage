package ru.ttv.cloudstorage.service.db;

import lombok.Getter;
import lombok.Setter;
import ru.ttv.cloudstorage.api.db.DBConnectionAPI;
import ru.ttv.cloudstorage.api.system.SettingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
@Getter
@Setter
public class DBConnection implements DBConnectionAPI {

    private Connection conn;

    @Inject
    private SettingService settingService;

    @Override
    public void init(){
        String url = "jdbc:sqlite:"+settingService.getSqlitePath();
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}