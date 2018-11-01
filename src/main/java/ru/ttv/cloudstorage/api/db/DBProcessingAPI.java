package ru.ttv.cloudstorage.api.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Timofey Teplykh
 */
public interface DBProcessingAPI {
    void initDB();

    String getItem(String tableType, String itemName, String parent, String type, long lastModified);

    int addItem(String tableType, String itemName, String parent, String type, long lastModified);

    boolean isDbEmpty() throws SQLException;

    ResultSet getAllItems(String tableType);

    int deleteItem(String tableType, String itemName, String parent, String type, long lastModified);
}