package ru.ttv.cloudstorage.service.db;

import ru.ttv.cloudstorage.api.db.DBConnectionAPI;
import ru.ttv.cloudstorage.api.db.DBProcessingAPI;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.sql.*;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class DBProcessing implements DBProcessingAPI {

    @Inject
    private DBConnectionAPI conn;

    public DBProcessing(){

    }

    public DBProcessing(DBConnection conn){
        this.conn = conn;
    }

    @Override
    public void initDB(){
        try {
            Statement stmt = conn.getConn().createStatement();
            stmt.execute(getInitialQueryLocal());
            stmt.execute(getInitialQueryRemote());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getInitialQueryLocal() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS  local_items (");
        sb.append("ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,");
        sb.append("ITEM_NAME TEXT NOT NULL,");
        sb.append("PARENT TEXT NOT NULL,");
        sb.append("ITEM_TYPE TEXT NOT NULL,");
        sb.append("LAST_MODIFIED INTEGER NOT NULL); ");
        //sb.append("DELETE FROM local_items;");
        return sb.toString();
    }

    private String getInitialQueryRemote() {
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE IF NOT EXISTS  remote_items (");
        sb.append("ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,");
        sb.append("ITEM_NAME TEXT NOT NULL,");
        sb.append("PARENT TEXT NOT NULL,");
        sb.append("ITEM_TYPE TEXT NOT NULL,");
        sb.append("LAST_MODIFIED INTEGER NOT NULL);");
        //sb.append("DELETE FROM remote_items;");
        return sb.toString();
    }

    @Override
    public String getItem(String tableType, String itemName, String parent, String type, long lastModified){
        String query = "SELECT * FROM "+tableType+"_items WHERE ITEM_NAME = ? AND PARENT = ? AND ITEM_TYPE = ? AND LAST_MODIFIED = ?";
        PreparedStatement pstmnt = null;
        try {
            pstmnt = conn.getConn().prepareStatement(query);
            pstmnt.setString(1,itemName);
            pstmnt.setString(2,parent);
            pstmnt.setString(3,type);
            pstmnt.setInt(4,(int)(lastModified/1000));

            ResultSet rs = pstmnt.executeQuery();
            if(rs.next()){
                return rs.getString(2);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ResultSet getAllItems(String tableType){
        String query = "SELECT * FROM "+tableType+"_items";
        PreparedStatement pstmnt = null;
        try {
            Statement statement = conn.getConn().createStatement();
            ResultSet rs = statement.executeQuery(query);
            return rs;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int addItem(String tableType, String itemName, String parent, String type, long lastModified){
        String query = "INSERT INTO "+tableType+"_items (ITEM_NAME, PARENT, ITEM_TYPE, LAST_MODIFIED) VALUES (?,?,?,?)";
        PreparedStatement pstmnt = null;
        try {
            pstmnt = conn.getConn().prepareStatement(query);
            pstmnt.setString(1,itemName);
            pstmnt.setString(2,parent);
            pstmnt.setString(3,type);
            pstmnt.setInt(4,(int)(lastModified/1000));
            return pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int deleteItem(String tableType, String itemName, String parent, String type, long lastModified) {
        String query = "DELETE FROM "+tableType+"_items WHERE ITEM_NAME = ? AND PARENT = ? AND ITEM_TYPE = ? AND LAST_MODIFIED = ?";
        PreparedStatement pstmnt = null;
        try {
            pstmnt = conn.getConn().prepareStatement(query);
            pstmnt.setString(1,itemName);
            pstmnt.setString(2,parent);
            pstmnt.setString(3,type);
            pstmnt.setInt(4,(int)(lastModified/1000));
            return pstmnt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean isDbEmpty() throws SQLException{
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM local_items ");
        sb.append("UNION ");
        sb.append("SELECT * FROM remote_items ");
        Statement statement = conn.getConn().createStatement();
        ResultSet rs = statement.executeQuery(sb.toString());
        if(rs.next()){
            return false;
        }
        return true;
    }

}
