package ru.ttv.cloudstorage.dto;

/**
 * @author Timofey Teplykh
 */
public class ResultDTO {
    private boolean status;

    public ResultDTO() {
    }

    public ResultDTO(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "status=" + status +
                '}';
    }
}
