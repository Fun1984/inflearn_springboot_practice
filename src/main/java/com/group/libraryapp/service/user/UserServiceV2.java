package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.request.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {
    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request) {
        User u = userRepository.save(new User(request.getName(), request.getAge()));
    }

    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream() //findAll = select * from user
//                .map(user -> new UserResponse(user.getId(), user.getName(), user.getAge()))
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    public void updateUser(UserUpdateRequest request) {
        // select * from user where id = ?'
        //Optional<User>
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName()); //객체를 업데이트하고
        userRepository.save(user); //세이브 메소드 호출 -> 자동으로 update sql이 날라가게 됨.
    }

    public void deleteUser(String name) {

        // SELECT * FROM user where name = ?
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
//        if (user == null) {
//            throw new IllegalArgumentException();
//        }
        userRepository.delete(user); // 주어진 sql 데이터 제거 기능.


        /*
        if (!userRepository.existsByName(name)) {
            throw new IllegalArgumentException();
        }
        User user = userRepository.findByName(name);
        userRepository.delete(user);
         */
    }

}
