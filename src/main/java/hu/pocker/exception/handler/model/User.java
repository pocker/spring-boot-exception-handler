package hu.pocker.exception.handler.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {

    private String username;

    private String name;

    private Role role;

}
