package com.immutable.request.utils;

import com.immutable.request.accounts.user.UserDTO;

public class UncertainResponse  extends UserDTO {
    private String defaultError;
    private String message;
    public UncertainResponse(String err, String msg){
        this.defaultError = err;
        this.message = msg;
    }

    public String getDefaultError() {
        return defaultError;
    }

    public String getMessage() {
        return message;
    }

    public void setDefaultError(String defaultError) {
        this.defaultError = defaultError;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
