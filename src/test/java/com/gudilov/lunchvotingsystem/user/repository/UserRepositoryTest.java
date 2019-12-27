package com.gudilov.lunchvotingsystem.user.repository;

import com.gudilov.lunchvotingsystem.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCrudRepository() {

        List<User> users = userRepository.getAll();

        System.out.println(users);

    }

}