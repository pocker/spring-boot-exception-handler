package hu.pocker.exception.handler.exception;

import hu.pocker.exception.handler.model.Role;
import lombok.Getter;

@Getter
public class RoleNotSupportedException extends RuntimeException {

    private final Role role;

    public RoleNotSupportedException(Role role) {
        this.role = role;
    }
}
