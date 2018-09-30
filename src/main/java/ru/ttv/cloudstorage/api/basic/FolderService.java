package ru.ttv.cloudstorage.api.basic;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Timofey Teplykh
 */
public interface FolderService {

    @NotNull
    List<String> getListFolderName();

    @NotNull
    List<String> getListFolderNameRoot();

    void createFolder(String folderName);
}
