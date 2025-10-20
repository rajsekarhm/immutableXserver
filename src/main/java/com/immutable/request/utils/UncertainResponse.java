package com.immutable.request.utils;

import com.immutable.request.accounts.user.UserDTO;

public class UncertainResponse  extends UserDTO {
    String DefaultError;
    String Message;
    public UncertainResponse(String err, String msg){
        this.DefaultError = err;
        this.Message = msg;
    }

    public String getDefaultError() {
        return DefaultError;
    }

    public String getMessage() {
        return Message;
    }

    public void setDefaultError(String defaultError) {
        DefaultError = defaultError;
    }

    public void setMessage(String message) {
        Message = message;
    }
}
