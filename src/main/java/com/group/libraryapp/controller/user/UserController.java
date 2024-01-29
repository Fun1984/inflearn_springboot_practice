package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.request.response.UserResponse;
import com.group.libraryapp.service.fruit.FruitService;
import com.group.libraryapp.service.user.UserServiceV1;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    /*
    //@Autowired
    private UserService userService;
    */

    private final UserServiceV1 userServiceV1;
    private final FruitService fruitService;

    /*
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    */

    // @Autowired //스프링 주입 @. 원래는 써야되나 업데이트로, 생성자에 대해 자동 적용됨.
    public UserController(UserServiceV1 userServiceV1, @Qualifier("main") FruitService fruitService) {
        this.userServiceV1 = userServiceV1;
        this.fruitService = fruitService;
    }


    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        userServiceV1.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        return userServiceV1.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        userServiceV1.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userServiceV1.deleteUser(name);
    }
}
