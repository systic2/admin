package com.example.admin.repository;

import com.example.admin.AdminApplication;
import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserRepositoryTest extends AdminApplicationTests {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        user.setAccount("testUser03");
        user.setEmail("testUser03@naver.com");
        user.setPhoneNumber("010-3333-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);
        System.out.println("newUser: " + newUser);
    }

    @Test
    public void read(){
        Optional<User> user = userRepository.findById(4L);
        user.ifPresent(selectUser -> {
            System.out.println("user : " + selectUser);
            System.out.println(selectUser.getEmail());
        });
    }

    @Test
//    @Transactional
    public void update() {
        Optional<User> user = userRepository.findById(4L);
        user.ifPresent(selectUser -> {
            selectUser.setAccount("happy");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("admin");

            userRepository.save(selectUser);
        });
    }

    @Test
    public void delete() {
        Optional<User> user = userRepository.findById(4L);
        assertTrue(user.isPresent()); // true

        user.ifPresent(selectUser -> {
            userRepository.delete(selectUser);
        });
        Optional<User> deleteUser = userRepository.findById(4L); //select
        if(deleteUser.isPresent()){
            System.out.println("데이터가 있다: "+ deleteUser.get());
        }else{
            System.out.println("데이터 삭제 완료");
        }
//        assertTrue(deleteUser.isPresent()); // false
    }
}
