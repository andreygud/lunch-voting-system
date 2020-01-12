package com.gudilov.lunchvotingsystem.user.web;

import com.gudilov.lunchvotingsystem.user.service.UserService;
import com.gudilov.lunchvotingsystem.user.to.UserCreateTo;
import com.gudilov.lunchvotingsystem.user.to.UserUpdateTo;
import com.gudilov.lunchvotingsystem.user.to.UserViewTo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.gudilov.lunchvotingsystem.common.utils.ValidationUtil.assureIdConsistent;

@RestController
@RequestMapping(UserAdminRestController.REST_URL)
public class UserAdminRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    public static final String REST_URL = "/rest/admin/user";

    private UserService userService;

    public UserAdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserViewTo> getAll() {
        log.debug("rest getALL");
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserViewTo get(@PathVariable int id) {
        log.debug("rest get id={}", id);
        return userService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.debug("rest delete id={}", id);
        userService.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody UserUpdateTo userUpdateTo, @PathVariable int id) {
        log.debug("rest update updateTo={}", userUpdateTo);
        assureIdConsistent(userUpdateTo, id);
        userService.update(userUpdateTo);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserViewTo> create(@Valid @RequestBody UserCreateTo userCreateTo) {
        log.debug("rest create userTo={}", userCreateTo);
        UserViewTo userViewTo = userService.create(userCreateTo);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(userViewTo.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(userViewTo);
    }
}
