package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.request.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate; //jdbc 탬플릿 선언

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }//생성자를 만들지 않아도 스프링이 jdbc템플릿 안에 정보를 넣어준다.

    @PostMapping("/user") // POST /user
    public void saveUser(@RequestBody UserCreateRequest request) {
        String sql = "INSERT INTO user (name, age) VALUES (?, ?)"; // ?는 문자열 변수를 의미
        jdbcTemplate.update(sql, request.getName(), request.getAge()); //sql을 문장으로 쓰고, 첫번째 ?에는 getName, 두번째 ?에는 getAge를 넣어준다.
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers() {
        String sql = "SELECT * FROM user";
        /*
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>() {
            //Crtl + O 를 눌러준다. -> MapRow를 오버라이드 해준다.
            // map.row는 유저 정보를 선언한 타입인 userResponse로 바꿔준다.
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name"); //ResultSet인 rs에서 string 타입의 name을 가져온다.
                int age = rs.getInt("age");
                return new UserResponse(id,name,age);
            }
        });
        */

        //람다식 - RowMapper 에다가 람다식 전환
        // 람다를 잘모른다면 자발개발자의 코틀린 입문 강의의 람다식 미리보기 강의 확인해볼 것(20분짜리)
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            return new UserResponse(id,name,age);
        });
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
        String sql = "UPDATE user SET name = ? WHERE id = ?";

        jdbcTemplate.update(sql, request.getName(), request.getId());

    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        String sql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(sql, name);
    }
}
