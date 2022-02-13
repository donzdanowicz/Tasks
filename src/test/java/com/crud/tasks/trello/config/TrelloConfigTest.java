package com.crud.tasks.trello.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TrelloConfigTest {

    @Autowired
    TrelloConfig trelloConfig;

    @Test
    void getTrelloApiEndpoint() {
        //Given & When
        String trelloApiEndpoint = trelloConfig.getTrelloApiEndpoint();
        //Then
        assertEquals("https://api.trello.com/1", trelloApiEndpoint);
    }

    @Test
    void getTrelloAppKey() {
        //Given & When
        String trelloAppKey = trelloConfig.getTrelloAppKey();
        //Then
        assertEquals("75813aa562d5ffb26aea43efa773bea6", trelloAppKey);
    }

    @Test
    void getTrelloToken() {
        //Given & When
        String trelloToken = trelloConfig.getTrelloToken();
        //Then
        assertEquals("28010a2ec58d7742e74ee06c1ce9172ca559ce66267e8cd343f5babf7f31a605", trelloToken);
    }

    @Test
    void getTrelloUsername() {
        //Given & When
        String trelloUsername = trelloConfig.getTrelloUsername();
        //Then
        assertEquals("przemysawzdanowicz", trelloUsername);
    }
}