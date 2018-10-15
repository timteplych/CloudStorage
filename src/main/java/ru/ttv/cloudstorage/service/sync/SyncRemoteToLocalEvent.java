package ru.ttv.cloudstorage.service.sync;


import ru.ttv.cloudstorage.api.local.FileLocalService;
import ru.ttv.cloudstorage.api.local.FolderLocalService;
import ru.ttv.cloudstorage.api.remote.FileRemoteService;
import ru.ttv.cloudstorage.api.remote.FolderRemoteService;
import ru.ttv.cloudstorage.api.sync.SyncRemoteToLocalEventAPI;
import ru.ttv.cloudstorage.api.system.ApplicationService;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Timofey Teplykh
 */
public class SyncRemoteToLocalEvent implements SyncRemoteToLocalEventAPI {

    @Inject
    public ApplicationService applicationService;

    @Inject
    public FolderRemoteService folderRemoteService;

    @Inject
    public FileRemoteService fileRemoteService;

    @Inject
    public FolderLocalService folderLocalService;

    @Inject
    public FileLocalService fileLocalService;

    @Override
    public void fire() {
        synchronize("");
    }

    private void synchronize(String folder) {
        List<String> subFolders = folderRemoteService.getListFolderNameRoot(folder);
        for (int i = 0; i <= subFolders.size()-1 ; i++) {
            String currentFolder = subFolders.get(i);
            folderLocalService.createFolder(folder+"/"+currentFolder);
            synchronizeFiles(folder+"/"+currentFolder);
            synchronize(folder+"/"+currentFolder);
        }
    }

    private void synchronizeFiles(String currentFolder) {
        List<String> files = fileRemoteService.getListFileNameRoot(currentFolder);
        for (int i = 0; i <= files.size()-1 ; i++) {
            if(!fileLocalService.exist(files.get(i),currentFolder)){
                fileLocalService.writeData(files.get(i),currentFolder,fileRemoteService.readData(files.get(i),currentFolder));
            }
        }
    }


}
