package com.group.libraryapp.dto.user.request;

import lombok.Getter;

@Getter //게터 생성 어노테이션 : 롬복
public class UserCreateRequest {

    private String name;
    private Integer age;

}
