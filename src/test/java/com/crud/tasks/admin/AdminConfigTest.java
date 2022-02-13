package com.crud.tasks.admin;


import com.crud.tasks.config.AdminConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AdminConfigTest {

    @Autowired
    private AdminConfig adminConfig;

    @Test
    public void shouldGetAdminMail() {
        //Given & When
        String adminMail = adminConfig.getAdminMail();
        //Then
        assertEquals("krzysztofchmiel89@gmail.com", adminMail);
    }



}
