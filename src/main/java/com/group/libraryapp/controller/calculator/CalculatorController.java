package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.calculator.request.CalculatorAddRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //api의 진입 지점 설정
public class CalculatorController {
/*
import org.springframework.web.bind.annotation.RequestParam;

    @GetMapping("/add") // Http Method : GET
    public int addTwoNumbers(
            @RequestParam int number1, //@RequestParam: 주어지는 쿼리를 함수 파라미터에 넣는다.
            @RequestParam int number2
    ) {
        return number1 + number2;
    }
*/

    //DTO 사용 방식
    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) { //@RequestParam 없이 메소드로도 받아올 수 있다. 직접 받아오는 방식?
        //CalculatorAddRequest에 Param값이 전달되는 데, 이걸 DTO(Data Transfer Object)라고 부른다.
        return request.getNumber1() + request.getNumber2();
    }
}
