package com.immutable.request.accounts;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Objects;

public class UserDAO {
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String password;
    public String location;
    public Long governmentID;
    public String edition;
    public Boolean isAgent;
    public Long securityId;
    public Boolean isAuthForBuyAndSell;
    public List<String> assetIds;
    public  List<String> tokenIds;
    public  UserDAO() {}
    @JsonCreator
    public UserDAO(
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("email") String email,
            @JsonProperty("phoneNumber") String phoneNumber,
            @JsonProperty("password") String password,
            @JsonProperty("location") String location,
            @JsonProperty("governmentID") Long governmentID,
            @JsonProperty("edition") String edition,
            @JsonProperty("isAgent") Boolean isAgent,
            @JsonProperty("securityId") Long securityId,
            @JsonProperty("isAuthForBuyAndSell") Boolean isAuthForBuyAndSell,
            @JsonProperty("assetIds") List<String> assetHolding,
            @JsonProperty("tokenIds") List<String> tokenHolding
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.location = location;
        this.governmentID = governmentID;
        this.edition = edition;
        this.isAgent = isAgent;
        this.securityId = securityId;
        this.isAuthForBuyAndSell = isAuthForBuyAndSell;
        this.assetIds = assetHolding;
        this.tokenIds =tokenHolding;
    }

    UserDAO(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.password = builder.password;
        this.location = builder.location;
        this.governmentID = builder.governmentID;
        this.edition = builder.edition;
        this.isAgent = builder.isAgent;
        this.securityId = builder.securityId;
        this.isAuthForBuyAndSell = builder.isAuthForBuyAndSell;
    }

    public List<String> getAssetIds() {
        return assetIds;
    }

    public List<String> getTokenIds() {
        return tokenIds;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public String getLocation() {
        return location;
    }

    public Long getGovernmentID() {
        return governmentID;
    }

    public String getEdition() {
        return edition;
    }

    public Boolean getIsAgent() {
        return isAgent;
    }

    public Long getsecurityId() {
        return securityId;
    }

    public Boolean getIsAuthForBuyAndSell() {
        return isAuthForBuyAndSell;
    }

    @Override
    public String toString() {
        return "UserDAO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' +
                ", location='" + location + '\'' +
                ", governmentID=" + governmentID +
                ", edition='" + edition + '\'' +
                ", isAgent=" + isAgent +
                ", securityId=" + securityId +
                ", isAuthForBuyAndSell=" + isAuthForBuyAndSell +
                ", tokenIds ="+ tokenIds +
                ", assetIds ="+ assetIds +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email, phoneNumber, password, location, governmentID,
                edition, isAgent, securityId, isAuthForBuyAndSell,tokenIds,assetIds);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDAO userDAO = (UserDAO) o;
        return Objects.equals(firstName, userDAO.firstName) &&
                Objects.equals(lastName, userDAO.lastName) &&
                Objects.equals(email, userDAO.email) &&
                Objects.equals(phoneNumber, userDAO.phoneNumber) &&
                Objects.equals(password, userDAO.password) &&
                Objects.equals(location, userDAO.location) &&
                Objects.equals(governmentID, userDAO.governmentID) &&
                Objects.equals(edition, userDAO.edition) &&
                Objects.equals(isAgent, userDAO.isAgent) &&
                Objects.equals(securityId, userDAO.securityId) &&
                Objects.equals(isAuthForBuyAndSell, userDAO.isAuthForBuyAndSell)&&
                Objects.equals(assetIds, userDAO.assetIds) &&
                Objects.equals(tokenIds, userDAO.tokenIds);
    }

    public static class Builder {
         String firstName;
         String lastName;
         String email;
        String phoneNumber;
         String password;
         String location;
         Long governmentID;
         String edition;
         Boolean isAgent;
         Long securityId;
         Boolean isAuthForBuyAndSell;
         List<String> assetIds;
          List<String> tokenIds;


        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setLocation(String location) {
            this.location = location;
            return this;
        }

        public Builder setGovernmentID(Long governmentID) {
            this.governmentID = governmentID;
            return this;
        }

        public Builder setEdition(String edition) {
            this.edition = edition;
            return this;
        }

        public Builder setIsAgent(Boolean isAgent) {
            this.isAgent = isAgent;
            return this;
        }

        public Builder setSecurityId(Long securityId) {
            this.securityId = securityId;
            return this;
        }

        public Builder setIsAuthForBuyAndSell(Boolean isAuthForBuyAndSell) {
            this.isAuthForBuyAndSell = isAuthForBuyAndSell;
            return this;
        }

        public Builder setTokenIds(List<String> _tokenIds){
            this.tokenIds = _tokenIds;
            return this;
        }

        public Builder setAssetIds(List<String> assetIds) {
            this.assetIds = assetIds;
            return this;

        }

        public UserDAO build() {
            return new UserDAO(firstName,lastName,email,phoneNumber,password,location,governmentID,edition,isAgent,securityId,isAuthForBuyAndSell,assetIds,
                    tokenIds);
        }

        public UserDAO _build(){
            return  new UserDAO(this);
        }
    }
}
