package ru.ttv.cloudstorage.api.basic;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @author Timofey Teplykh
 */
public interface FileService {

    @NotNull
    List<String> getListFileName();

    @Nullable
    Byte[] readData(String name);

    boolean exist(String name);

    void remove(String name);

    void createTextFile(String name, String text);

}
