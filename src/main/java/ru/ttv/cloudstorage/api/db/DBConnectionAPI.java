package ru.ttv.cloudstorage.api.db;

import java.sql.Connection;

/**
 * @author Timofey Teplykh
 */
public interface DBConnectionAPI {

    void init();

    void close();

    Connection getConn();

    void setConn(Connection conn);

}