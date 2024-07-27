package com.immutable.request.accounts;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserDAO {
    String userName;
    String email;
    Boolean isAgent;
    Long phoneNumber;
    String edition;
    Boolean isAuthForBuyAndSell;
    Long governmentID;
    public  static class Builder {
        UserDAO userIMX;
        public UserDAO build(){
            this.userIMX = new UserDAO();
            return this.userIMX;
        }
    }
}
