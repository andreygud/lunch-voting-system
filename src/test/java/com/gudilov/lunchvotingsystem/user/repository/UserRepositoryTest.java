package com.gudilov.lunchvotingsystem.user.repository;

import com.gudilov.lunchvotingsystem.user.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-db.xml",
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-mvc.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCrudRepository() {

        List<User> users = userRepository.getAll();

        assertEquals(2,users.size());

    }


}