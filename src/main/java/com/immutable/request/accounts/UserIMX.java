package com.immutable.request.accounts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserIMX {
    String userName;
    String email;
    Boolean isAgent;
    Long phoneNumber;
    String edition;
    Boolean isAuthForBuyAndSell;
    Long governmentID;
    public  static class Builder {
        UserIMX userIMX;
        public UserIMX build(){
            this.userIMX = new UserIMX();
            return this.userIMX;
        }
    }
}
