package ru.ttv.cloudstorage.api.basic;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * @author Timofey Teplykh
 */
public interface FolderService {

    void printListFolderNameRoot();

    @NotNull
    List<String> getListFolderNameRoot(String folderName);

    void createFolder(String folderName);

    void removeFolder(String folderName);

    void clearRoot();

    boolean folderExist(String folderName);

}
