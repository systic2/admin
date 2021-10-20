package com.example.admin.controller;

import com.example.admin.model.SearchParam;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GetController {
    @RequestMapping(method = RequestMethod.GET, path = "/getMethod")
    public String getRequest(){
        return "Hi getMethod";
    }
    @GetMapping("/getParameter") // localhost:9090/api/getParameter?id=hong&password=1234
    public String getParameter(@RequestParam String id, @RequestParam String password){
        System.out.println("id : " + id);
        System.out.println("password : " + password);
        return id + " " + password;
    }
    /**
     * 2. 검색에 대한 파라미터 갯수가 많을 경우 객체로 받는다
     */
    @GetMapping("/getMultiParameter") // localhost:9090/api/getMultiParameter?account=hong&email=hong@naver.com&page=100
    public SearchParam getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        // 3. json 통신 {"account":"", "email":"", "page":0}
//        return "OK";
        return searchParam;
    }
}
