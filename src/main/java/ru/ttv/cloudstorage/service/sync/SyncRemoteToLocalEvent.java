package ru.ttv.cloudstorage.service.sync;


import ru.ttv.cloudstorage.api.db.DBProcessingAPI;
import ru.ttv.cloudstorage.api.local.FileLocalService;
import ru.ttv.cloudstorage.api.local.FolderLocalService;
import ru.ttv.cloudstorage.api.remote.FileRemoteService;
import ru.ttv.cloudstorage.api.remote.FolderRemoteService;
import ru.ttv.cloudstorage.api.sync.SyncRemoteToLocalEventAPI;
import ru.ttv.cloudstorage.api.system.ApplicationService;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Timofey Teplykh
 */
public class SyncRemoteToLocalEvent implements SyncRemoteToLocalEventAPI {

    private static final String TYPE_FOLDER = "folder";

    private static final String TYPE_FILE = "file";

    private static final String DISPOSITION_LOCAL = "local";

    private static final String DISPOSITION_REMOTE = "remote";

    @Inject
    private FolderRemoteService folderRemoteService;

    @Inject
    private FileRemoteService fileRemoteService;

    @Inject
    private FolderLocalService folderLocalService;

    @Inject
    private FileLocalService fileLocalService;

    @Inject
    private DBProcessingAPI dbProcessing;

    @Override
    public void fire() {
        synchronize("");
        // Пробегаемся по римоут ДБ и смотрим что есть в римоут ДБ и чего одновременно нет на римоут хранилище
        // и удаляем это в локальном каталоге и БД и в римоут бд
        ResultSet resultSet = dbProcessing.getAllItems(DISPOSITION_REMOTE);
        try {
            while(resultSet.next()){
                String path ="/"+resultSet.getString(3)+"/"+resultSet.getString(2);
                if(TYPE_FOLDER.equals(resultSet.getString(4))){
                    if(!folderRemoteService.folderExist(resultSet.getString(3)+"/"+resultSet.getString(2))){
                        if(folderLocalService.folderExist(path)){
                            folderLocalService.removeFolder(path);
                        }
                        dbProcessing.deleteItem(DISPOSITION_LOCAL, resultSet.getString(2), resultSet.getString(3),TYPE_FOLDER, 25888444444L);
                        dbProcessing.deleteItem(DISPOSITION_REMOTE, resultSet.getString(2), resultSet.getString(3),TYPE_FOLDER, 25888444444L);
                    }
                }else{
                    if(!fileRemoteService.exist(resultSet.getString(2), resultSet.getString(3))) {
                        if(fileLocalService.exist(resultSet.getString(2),resultSet.getString(3))){
                            fileLocalService.remove(path);
                        }

                        dbProcessing.deleteItem(DISPOSITION_LOCAL, resultSet.getString(2), resultSet.getString(3), TYPE_FILE, 25888444444L);
                        dbProcessing.deleteItem(DISPOSITION_REMOTE, resultSet.getString(2), resultSet.getString(3), TYPE_FILE, 25888444444L);
                    }
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void synchronize(String folder) {
        List<String> subFolders = folderRemoteService.getListFolderNameRoot(folder);
        for (int i = 0; i <= subFolders.size()-1 ; i++) {
            String currentFolder = subFolders.get(i);
            if(!folderLocalService.folderExist(folder+"/"+currentFolder)){
                folderLocalService.createFolder(folder+"/"+currentFolder);
            }
            if(dbProcessing.getItem(DISPOSITION_REMOTE, currentFolder, folder,TYPE_FOLDER,25888444444L) == null){
                dbProcessing.addItem(DISPOSITION_REMOTE,currentFolder, folder, TYPE_FOLDER,25888444444L);
            }
            if(dbProcessing.getItem(DISPOSITION_LOCAL, currentFolder, folder,TYPE_FOLDER,25888444444L) == null){
                dbProcessing.addItem(DISPOSITION_LOCAL,currentFolder, folder, TYPE_FOLDER,25888444444L);
            }
            synchronizeFiles(folder+"/"+currentFolder);
            synchronize(folder+"/"+currentFolder);
        }
    }

    private void synchronizeFiles(String currentFolder) {
        List<String> files = fileRemoteService.getListFileNameRoot(currentFolder);
        for (int i = 0; i <= files.size()-1 ; i++) {
            if(!fileLocalService.exist(files.get(i),currentFolder)){
                fileLocalService.writeData(files.get(i),currentFolder,fileRemoteService.readData(files.get(i),currentFolder));
                if(dbProcessing.getItem(DISPOSITION_REMOTE, files.get(i), currentFolder,"file",25888444444L) == null){
                    dbProcessing.addItem(DISPOSITION_REMOTE,files.get(i), currentFolder, "file",25888444444L);
                }
                if(dbProcessing.getItem(DISPOSITION_LOCAL, files.get(i), currentFolder,"file",25888444444L) == null){
                    dbProcessing.addItem(DISPOSITION_LOCAL,files.get(i), currentFolder, "file",25888444444L);
                }
            }
        }
    }
}