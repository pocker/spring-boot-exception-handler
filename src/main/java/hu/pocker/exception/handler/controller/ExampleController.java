package hu.pocker.exception.handler.controller;

import hu.pocker.exception.handler.exception.RoleNotSupportedException;
import hu.pocker.exception.handler.model.Role;
import hu.pocker.exception.handler.model.User;
import hu.pocker.exception.handler.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Try the following calls:
 *  http://localhost:8080/users/USER - Should list the users without any problem
 *  http://localhost:8080/users/ADMIN - Should get an error message and a custom log
 *  http://localhost:8080/users/ASD - Should get an error message and a custom log with useful details about the error and a stack trace.
 */
@Log4j2
@RestController
public class ExampleController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{role}")
    public List<User> getUsersByRole(@PathVariable final Role role){
        return userService.findUsersByRole(role);
    }

    /**
     * Handle the {@link RoleNotSupportedException} and creates a detailed error response for the client.
     * @param exception - the role violation exception
     * @return - client response
     */
    @ExceptionHandler(RoleNotSupportedException.class)
    public ResponseEntity<String> roleNotSupportedHandler(RoleNotSupportedException exception){
        final var role = exception.getRole();
        log.warn("List users with {} role is not allowed.", role);
        return ResponseEntity
                 .status(HttpStatus.FORBIDDEN)
                .body("Ups! We are not allow to list users with " + role + " role.");
    }

    /**
     * Catch any unhandled exceptions and creates a general response for the client.
     * @param exception - the unhandled exception
     * @return - client response
     */
    @ExceptionHandler
    public ResponseEntity<String> generalExceptionHandler(Exception exception){
        log.error("Unexpected exception!", exception);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ups! Something went wrong in our end.");
    }

}
