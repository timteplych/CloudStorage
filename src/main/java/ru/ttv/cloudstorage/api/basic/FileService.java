package ru.ttv.cloudstorage.api.basic;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Timofey Teplykh
 */
public interface FileService {

    @NotNull
    List<String> getListFileNameRoot(String folder);

    void printListFileNameRoot();

    void clearRoot();

    @Nullable
    byte[] readData(String name, String folder);

    void writeData(String fileName, String folder, byte[] data);

    boolean exist(String name, String folder);

    void remove(String name);

    void createTextFile(String fileName, String folder, String text);

}
