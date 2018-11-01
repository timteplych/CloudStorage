package ru.ttv.cloudstorage.service.local;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.ttv.cloudstorage.api.local.FileLocalService;
import ru.ttv.cloudstorage.api.system.SettingService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class FileLocalServiceBean implements FileLocalService {

    @Inject
    private SettingService settingService;

    @NotNull
    @Override
    public List<String> getListFileNameRoot(final String folder) {
        final File file = new File(settingService.getSyncFolder()+folder);
        final String[] files = file.list((current, name) -> new File(current,name).isFile());
        if(files == null) return Collections.emptyList();
        return Arrays.asList(files);
    }

    @Override
    public void printListFileNameRoot() {
        for (String name: getListFileNameRoot("")){
            System.out.println(name);
        }
    }

    @Override
    public void clearRoot() {
        final File root = new File(settingService.getSyncFolder());
        final List<String> files = getListFileNameRoot(settingService.getSyncFolder());
        for(final String name: files){
            final File file = new File(root,name);
            file.delete();
        }
    }

    @Nullable
    @Override
    @SneakyThrows
    public byte[] readData(@Nullable final String name, String folder) {
        if(name == null || name.isEmpty()) return new byte[]{};
        final File file = new File(getRoot()+folder,name);
        return Files.readAllBytes(file.toPath());
    }

    @Override
    public boolean exist(@Nullable final String name, String folder) {
        if(name == null || name.isEmpty()) return false;
        final File file = new File(getRoot()+folder, name);
        return file.exists();
    }

    @Override
    public void remove(@Nullable final String name) {
        if(name == null || name.isEmpty()) return;
        final File file = new File(getRoot()+name);
        file.delete();
    }

    @Override
    @SneakyThrows
    public void writeData(@Nullable final String name, String folder, byte[] data ) {
        if(name == null || name.isEmpty()) return;
        final File file = new File(getRoot()+folder, name);
        final Path path = Paths.get(file.toURI());
        Files.write(path,data);
    }

    @Override
    public void createTextFile(@Nullable final String fileName, String folder, @Nullable final String text) {
        if(text == null) return;
        writeData(fileName, folder, text.getBytes());
    }

    @NotNull
    private File getRoot(){
        return new File(settingService.getSyncFolder());
    }
}
