package hu.pocker.exception.handler.service;

import hu.pocker.exception.handler.exception.RoleNotSupportedException;
import hu.pocker.exception.handler.model.Role;
import hu.pocker.exception.handler.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class UserService {

    private List<User> users;

    /**
     * Sample data generator.
     */
    @PostConstruct
    public void postConstruct() {
        users = IntStream.range(0, 10).mapToObj(i -> new User(
                "user" + i,
                "Example User" + i,
                Role.values()[i % Role.values().length]
        )).collect(Collectors.toList());
    }

    /**
     * Just a simple function witch can list users with the given {@link Role}.
     * @param role - role filter.
     * @return - filtered users
     */
    public List<User> findUsersByRole(Role role) {

        if(Role.ADMIN == role){
            throw new RoleNotSupportedException(role);
        }

        return users.stream()
                .filter(user -> Objects.equals(user.getRole(), role))
                .collect(Collectors.toList());
    }
}
