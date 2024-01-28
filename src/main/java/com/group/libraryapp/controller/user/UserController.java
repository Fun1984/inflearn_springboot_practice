package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.request.response.UserResponse;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    /*
    //@Autowired
    private UserService userService;
    */

    private final UserService userService;
    private final FruitService fruitService;

    /*
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    */

    // @Autowired //스프링 주입 @. 원래는 써야되나 업데이트로, 생성자에 대해 자동 적용됨.
    public UserController(UserService userService, @Qualifier("main") FruitService fruitService) {
        this.userService = userService;
        this.fruitService = fruitService;
    }


    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        userService.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }
}
