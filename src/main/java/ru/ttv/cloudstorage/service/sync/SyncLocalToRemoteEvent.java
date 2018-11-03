package ru.ttv.cloudstorage.service.sync;


import lombok.SneakyThrows;
import org.jetbrains.annotations.Nullable;
import ru.ttv.cloudstorage.api.db.DBProcessingAPI;
import ru.ttv.cloudstorage.api.local.FileLocalService;
import ru.ttv.cloudstorage.api.local.FolderLocalService;
import ru.ttv.cloudstorage.api.remote.FileRemoteService;
import ru.ttv.cloudstorage.api.remote.FolderRemoteService;
import ru.ttv.cloudstorage.api.sync.SyncLocalToRemoteEventAPI;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * @author Timofey Teplykh
 */
public class SyncLocalToRemoteEvent implements SyncLocalToRemoteEventAPI {

    private static final String TYPE_FOLDER = "folder";

    private static final String TYPE_FILE = "file";

    private static final String DISPOSITION_LOCAL = "local";

    private static final String DISPOSITION_REMOTE = "remote";

    @Inject
    private FolderLocalService folderLocalService;

    @Inject
    private FileLocalService fileLocalService;

    @Inject
    private FolderRemoteService folderRemoteService;

    @Inject
    private FileRemoteService fileRemoteService;

    @Inject
    private DBProcessingAPI dbProcessing;


    @Override
    @SneakyThrows
    public void fire() {
        synchronize("");
        final ResultSet resultSet = dbProcessing.getAllItems(DISPOSITION_LOCAL);
            while(resultSet.next()){
                String path =resultSet.getString(3)+"/"+resultSet.getString(2);
                if(TYPE_FOLDER.equals(resultSet.getString(4))){
                    if(!folderLocalService.folderExist(path)){
                        if(folderRemoteService.folderExist(resultSet.getString(3)+"/"+resultSet.getString(2))){
                            folderRemoteService.removeFolder(resultSet.getString(3)+"/"+resultSet.getString(2));
                        }
                        dbProcessing.deleteItem(DISPOSITION_LOCAL, resultSet.getString(2), resultSet.getString(3),TYPE_FOLDER, 25888444444L);
                        dbProcessing.deleteItem(DISPOSITION_REMOTE, resultSet.getString(2), resultSet.getString(3),TYPE_FOLDER, 25888444444L);
                    }
                }else{
                    if(!fileLocalService.exist(resultSet.getString(2), "/"+resultSet.getString(3))) {
                        if(fileRemoteService.exist(resultSet.getString(2), resultSet.getString(3))) {
                            fileRemoteService.remove(path);
                        }
                        dbProcessing.deleteItem(DISPOSITION_LOCAL, resultSet.getString(2), resultSet.getString(3), TYPE_FILE, 25888444444L);
                        dbProcessing.deleteItem(DISPOSITION_REMOTE, resultSet.getString(2), resultSet.getString(3), TYPE_FILE, 25888444444L);
                    }
                }
            }
    }

    private void synchronize(@Nullable final String folder) {
        final List<String> subFolders = folderLocalService.getListFolderNameRoot(folder);
        for (int i = 0; i <= subFolders.size()-1 ; i++) {
            String currentFolder = subFolders.get(i);
            if(!folderRemoteService.folderExist(folder+"/"+currentFolder)){
                folderRemoteService.createFolder(folder+"/"+currentFolder);
            }
            if(dbProcessing.getItem(DISPOSITION_LOCAL, currentFolder, folder,TYPE_FOLDER,25888444444L) == null){
                dbProcessing.addItem(DISPOSITION_LOCAL,currentFolder, folder, TYPE_FOLDER,25888444444L);
            }
            if(dbProcessing.getItem(DISPOSITION_REMOTE, currentFolder, folder,TYPE_FOLDER,25888444444L) == null){
                dbProcessing.addItem(DISPOSITION_REMOTE,currentFolder, folder, TYPE_FOLDER,25888444444L);
            }
            synchronizeFiles(folder+"/"+currentFolder);
            synchronize(folder+"/"+currentFolder);
        }
    }

    private void synchronizeFiles(@Nullable final String currentFolder) {
        final List<String> files = fileLocalService.getListFileNameRoot(currentFolder);
        for (int i = 0; i <= files.size()-1 ; i++) {
            if(!fileRemoteService.exist(files.get(i),currentFolder)){
                fileRemoteService.writeData(files.get(i),currentFolder,fileLocalService.readData(files.get(i),currentFolder));
                if(dbProcessing.getItem(DISPOSITION_LOCAL, files.get(i), currentFolder,TYPE_FILE,25888444444L) == null){
                    dbProcessing.addItem(DISPOSITION_LOCAL,files.get(i), currentFolder, TYPE_FILE,25888444444L);
                }
                if(dbProcessing.getItem(DISPOSITION_REMOTE, files.get(i), currentFolder,TYPE_FILE,25888444444L) == null){
                    dbProcessing.addItem(DISPOSITION_REMOTE,files.get(i), currentFolder, TYPE_FILE,25888444444L);
                }
            }
        }
    }
}