package com.immutable.authorization;
import java.util.List;

public interface IAuthorization<T> {
    public Boolean isEditionUser();
    public List<T> accessibilty();
}
