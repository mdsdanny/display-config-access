package com.mdsdanny.display.beans;

/**
 *  Custom DisplayConfigAccessException an exception class for handling the error's responses.
 */
public class DisplayConfigAccessException extends Exception {

    private int status;
    private String message;

    public DisplayConfigAccessException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
