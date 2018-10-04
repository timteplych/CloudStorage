package ru.ttv.cloudstorage.handler.keyboard;

import lombok.SneakyThrows;
import ru.ttv.cloudstorage.api.local.FileLocalService;
import ru.ttv.cloudstorage.api.local.FolderLocalService;
import ru.ttv.cloudstorage.api.remote.FileRemoteService;
import ru.ttv.cloudstorage.api.remote.FolderRemoteService;
import ru.ttv.cloudstorage.api.system.ApplicationService;
import ru.ttv.cloudstorage.api.system.EndpointService;
import ru.ttv.cloudstorage.api.system.SyncService;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Timofey Teplykh
 */
@ApplicationScoped
public class KeyboardCommandHandler {

    public static final String CMD_HELP = "help";

    public static final String CMD_SYNC = "sync";
    public static final String CMD_EXIT = "exit";
    public static final String CMD_SOAP = "soap";
    public static final String CMD_LOGIN = "login";
    public static final String CMD_LOGOUT = "logout";
    public static final String CMD_START = "start";
    public static final String CMD_STOP = "stop";
    public static final String CMD_REMOTE_CLEAR = "remote-clearRoot";
    public static final String CMD_LOCAL_CLEAR = "local-clearRoot";
    public static final String CMD_REMOTE_FOLDERS = "remote-folders";
    public static final String CMD_LOCAL_FOLDERS = "local-folders";
    public static final String CMD_REMOTE_FILES = "remote-files";
    public static final String CMD_LOCAL_FILES = "local-files";
    public static final String CMD_LOCAL_FILES_CLEAR = "local-files-clear";
    public static final String CMD_REMOTE_FILES_CLEAR = "remote-files-clear";
    public static final String CMD_LOCAL_FOLDERS_CLEAR = "local-folders-clear";
    public static final String CMD_REMOTE_FOLDERS_CLEAR = "remote-folders-clear";

    @Inject
    private Event<KeyboardCommandEvent> keyboardCommandEvent;

    @Inject
    private ApplicationService applicationService;

    @Inject
    private SyncService syncService;

    @Inject
    private EndpointService endpointService;

    @Inject
    private FolderRemoteService folderRemoteService;

    @Inject
    private FolderLocalService folderLocalService;

    @Inject
    private FileLocalService fileLocalService;

    @Inject
    private FileRemoteService fileRemoteService;

    @SneakyThrows
    public void observe(@Observes final KeyboardCommandEvent event){
        System.out.println();
        System.out.println("cmd: ");
        final Scanner scanner = new Scanner(System.in);
        final String cmd = scanner.nextLine();

        switch (cmd){
            case CMD_HELP:
                help();
                break;
            case CMD_EXIT:
                applicationService.shutdown();
                break;
            case CMD_SYNC:
                syncService.sync();
                break;
            case CMD_START:
                syncService.start();
                break;
            case CMD_LOGOUT:
                applicationService.logout();
                break;
            case CMD_STOP:
                syncService.stop();
                break;
            case CMD_SOAP:
                endpointService.start();
                break;
            case CMD_LOGIN:
                applicationService.login();
                break;
            case CMD_REMOTE_CLEAR:
                folderRemoteService.clearRoot();
                fileRemoteService.clearRoot();
                break;
            case CMD_LOCAL_CLEAR:
                folderLocalService.clearRoot();
                fileLocalService.clearRoot();
                break;
            case CMD_REMOTE_FOLDERS:
                folderRemoteService.printListFolderNameRoot();
                break;
            case CMD_LOCAL_FOLDERS:
                folderLocalService.printListFolderNameRoot();
                break;
            case CMD_REMOTE_FILES:
                fileRemoteService.printListFolderNameRoot();
                break;
            case CMD_LOCAL_FILES:
                fileLocalService.printListFolderNameRoot();
                break;
            case CMD_REMOTE_FILES_CLEAR:
                fileRemoteService.clearRoot();
                break;
            case CMD_LOCAL_FILES_CLEAR:
                fileLocalService.clearRoot();
                break;
            case CMD_REMOTE_FOLDERS_CLEAR:
                folderRemoteService.clearRoot();
                break;
            case CMD_LOCAL_FOLDERS_CLEAR:
                folderLocalService.clearRoot();
                break;
            default:
                System.out.println("Undefined command...");
                break;
        }
        keyboardCommandEvent.fire(new KeyboardCommandEvent());
    }

    private void help(){
        final PrintStream stream = System.out;
        stream.println();
        stream.println(KeyboardCommandHandler.CMD_HELP + "    - Display list commands");
        stream.println(KeyboardCommandHandler.CMD_SYNC + "    - Call cloud synchronization");
        stream.println(KeyboardCommandHandler.CMD_EXIT + "    - Shutdown cloud application");
        stream.println(KeyboardCommandHandler.CMD_SOAP + "    - Initialize soap web-services");
        stream.println(KeyboardCommandHandler.CMD_LOGIN + "    - Open cloud session");
        stream.println(KeyboardCommandHandler.CMD_LOGOUT + "    - Close cloud session");
        stream.println(KeyboardCommandHandler.CMD_START + "    - Start cloud synchronization");
        stream.println(KeyboardCommandHandler.CMD_STOP + "    - Stop cloud synchronization");
        stream.println(KeyboardCommandHandler.CMD_LOCAL_FILES + "    - Display local root files");
        stream.println(KeyboardCommandHandler.CMD_LOCAL_FOLDERS + "    - Display local root folders");
        stream.println(KeyboardCommandHandler.CMD_REMOTE_FILES + "    - Display remote root files");
        stream.println(KeyboardCommandHandler.CMD_REMOTE_FOLDERS + "    - Display remote root folders");
        stream.println();
    }
}
