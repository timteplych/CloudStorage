package ru.ttv.cloudstorage.handler.keyboard;

import javax.enterprise.context.ApplicationScoped;

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

    
}
