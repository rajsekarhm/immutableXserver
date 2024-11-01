package com.immutable.request.accounts;


import java.util.Objects;

public class UserDAO {
    private String userName;
    private String email;
    private Boolean isAgent;
    private Long phoneNumber;
    private String edition;
    private Boolean isAuthForBuyAndSell;
    private Long governmentID;

    public  UserDAO(){}

    UserDAO(Builder builder) {
        this.userName = builder.userName;
        this.email = builder.email;
        this.isAgent = builder.isAgent;
        this.phoneNumber = builder.phoneNumber;
        this.edition = builder.edition;
        this.isAuthForBuyAndSell = builder.isAuthForBuyAndSell;
        this.governmentID = builder.governmentID;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Boolean getIsAgent() {
        return isAgent;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public String getEdition() {
        return edition;
    }

    public Boolean getIsAuthForBuyAndSell() {
        return isAuthForBuyAndSell;
    }

    public Long getGovernmentID() {
        return governmentID;
    }

    public static class Builder {
         String userName;
         String email;
         Boolean isAgent;
         Long phoneNumber;
         String edition;
         Boolean isAuthForBuyAndSell;
         Long governmentID;

        public Builder setUserName(String userName) {
            this.userName = userName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setIsAgent(Boolean isAgent) {
            this.isAgent = isAgent;
            return this;
        }

        public Builder setPhoneNumber(Long phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setEdition(String edition) {
            this.edition = edition;
            return this;
        }

        public Builder setIsAuthForBuyAndSell(Boolean isAuthForBuyAndSell) {
            this.isAuthForBuyAndSell = isAuthForBuyAndSell;
            return this;
        }

        public Builder setGovernmentID(Long governmentID) {
            this.governmentID = governmentID;
            return this;
        }

        public UserDAO build() {
            return new UserDAO(this);
        }
    }

    @Override
    public String toString() {
        return "UserDAO{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", isAgent=" + isAgent +
                ", phoneNumber=" + phoneNumber +
                ", edition='" + edition + '\'' +
                ", isAuthForBuyAndSell=" + isAuthForBuyAndSell +
                ", governmentID=" + governmentID +
                '}';
    }


    @Override
    public int hashCode() {
        return Objects.hash(userName, email, isAgent, phoneNumber, edition, isAuthForBuyAndSell, governmentID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDAO userDAO = (UserDAO) o;
        return Objects.equals(userName, userDAO.userName) &&
                Objects.equals(email, userDAO.email) &&
                Objects.equals(isAgent, userDAO.isAgent) &&
                Objects.equals(phoneNumber, userDAO.phoneNumber) &&
                Objects.equals(edition, userDAO.edition) &&
                Objects.equals(isAuthForBuyAndSell, userDAO.isAuthForBuyAndSell) &&
                Objects.equals(governmentID, userDAO.governmentID);
    }
}
