package ru.ttv.cloudstorage.service.local;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ttv.cloudstorage.api.annotation.Loggable;
import ru.ttv.cloudstorage.api.local.FolderLocalService;
import ru.ttv.cloudstorage.api.system.SettingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class FolderLocalServiceBean implements FolderLocalService {

    @Inject
    private SettingService settingService;

    @Loggable
    @Override
    public void init() {
        final String folder = settingService.getSyncFolder();
        final File file = new File(folder);
        file.mkdirs();
    }

    @Override
    @NotNull
    public List<String> getListFolderNameRoot(@Nullable final String directory) {
        final File root = new File(settingService.getSyncFolder()+directory);
        final String[] directories = root.list((current, name) -> new File(current,name).isDirectory());
        return Arrays.asList(directories);
    }

    @Override
    public void printListFolderNameRoot() {
        for (String name: getListFolderNameRoot("")){
            System.out.println(name);
        }
    }

    @Override
    public void createFolder(@Nullable final String folderName) {
        if(folderName == null || folderName.isEmpty()) return;
        final String folder = settingService.getSyncFolder();
        final File file = new File(folder + folderName);
        file.mkdirs();
    }

    @Override
    public void removeFolder(@Nullable final String folderName){
        if(folderName == null || folderName.isEmpty())return;
        final File file = new File( settingService.getSyncFolder() + folderName);
        file.delete();
    }

    @Override
    public void clearRoot() {

    }

    @NotNull
    private File getRoot(){
        return new File(settingService.getSyncFolder());
    }

    @Override
    public boolean folderExist(@Nullable final String folderName) {
        if(folderName == null || folderName.isEmpty()) return false;
        final File folder = new File(getRoot()+folderName);
        return folder.exists();
    }
}
