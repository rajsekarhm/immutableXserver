package com.immutable.authorization;
import java.util.List;

public interface IAuthorization {
    public Boolean isEditionUser();
    public List<String> accessibilty();
}
