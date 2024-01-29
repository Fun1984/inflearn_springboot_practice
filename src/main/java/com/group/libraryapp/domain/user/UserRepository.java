package com.group.libraryapp.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    //상속만으로 @Repository는 적용된다.

    Optional<User> findByName(String name); //반환타입 User이고, 유저가 있다면 반환 없으면 null. 함수이름만 작성하면 알아서 SQL이 조립된다.
    // find라고 작성 시, 1개의 데이터만 가져옴
        // findAll로, 결과물이 N개인 경우 사용. List<타입> 반환
    // By뒤에 붙는 필드 이름으로 SELECT 쿼리의 WHERE 문 작성
    // 이를 통해, SELECT * FROM user WHERE name = ?; 를 수행.

    //exists : 쿼리 결과가 존재하는 지 반환. boolean 반환
    //boolean existsByName(String name);

    //count : sql 결과값의 갯수를 센다.
    //long countByAge(Integer age);

    //By 뒤에 들어오는 구절에는 And나 Or로 조합할 수 있다.
    //GreaterThan : 초과
    //GreaterThanEqual : 이상
    //LessThan : 미만
    //LessThanEqual : 이하
    //Between : 사이에
    //StartsWith : ~로 시작하는
    //EndsWith: ~로 끝나는
    //ex) List<User> findAllByAgeBetween(int startAge, int endAge);
}
