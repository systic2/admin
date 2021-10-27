package com.example.admin.repository;

import com.example.admin.AdminApplicationTests;
import com.example.admin.model.entity.Item;
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
        user.setAccount("testUser05");
        user.setEmail("testUser05@naver.com");
        user.setPhoneNumber("010-5555-5555");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("admin");

        User newUser = userRepository.save(user);
        System.out.println("newUser: " + newUser);
    }

    @Test
    @Transactional
    public void read(){
//        Optional<User> user = userRepository.findById(1L);
        Optional<User> user = userRepository.findByAccount("testUser03");
        user.ifPresent(selectUser -> { // id = 1인 유저가 있을 때 어떤 상품을 구입했는지 알 수 있다.
            selectUser.getOrderDetailList().stream().forEach(detail -> { // 주문내역을 List로 받는다.
//                System.out.println(detail.getId());
                Item item = detail.getItem();

                System.out.println(detail.getItem());
            });
//            System.out.println("user : " + selectUser);
//            System.out.println(selectUser.getEmail());
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
