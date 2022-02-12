package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TrelloMapperTest {

    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void shouldMapToBoards() {
        //Given
        List<TrelloBoardDto> boardDtoList = new ArrayList<>();
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "listDto", true);
        trelloListDtoList.add(trelloListDto);
        TrelloBoardDto trelloBoardDto = new TrelloBoardDto("1", "boardDto", trelloListDtoList);
        boardDtoList.add(trelloBoardDto);

        //When
        List<TrelloBoard> trelloBoard = trelloMapper.mapToBoards(boardDtoList);

        //Then
        assertEquals(1, trelloBoard.size());
        assertEquals("1", trelloBoard.get(0).getId());
        assertEquals("boardDto", trelloBoard.get(0).getName());
        assertEquals("1", trelloBoard.get(0).getLists().get(0).getId());
        assertEquals("listDto", trelloBoard.get(0).getLists().get(0).getName());
        assertTrue(trelloBoard.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void shouldMapToBoardsDto() {
        //Given
        List<TrelloBoard> boardList = new ArrayList<>();
        List<TrelloList> trelloListList = new ArrayList<>();
        TrelloList trelloList = new TrelloList("2", "list", false);
        trelloListList.add(trelloList);
        TrelloBoard trelloBoard = new TrelloBoard("2", "board", trelloListList);
        boardList.add(trelloBoard);

        //When
        List<TrelloBoardDto> trelloBoardDto = trelloMapper.mapToBoardsDto(boardList);

        //Then
        assertEquals(1, trelloBoardDto.size());
        assertEquals("2", trelloBoardDto.get(0).getId());
        assertEquals("board", trelloBoardDto.get(0).getName());
        assertEquals("2", trelloBoardDto.get(0).getLists().get(0).getId());
        assertEquals("list", trelloBoardDto.get(0).getLists().get(0).getName());
        assertFalse(trelloBoardDto.get(0).getLists().get(0).isClosed());
    }

    @Test
    public void shouldMapToList() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("1", "listDto", true);
        trelloListDtoList.add(trelloListDto);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(1, trelloLists.size());
        assertEquals("1", trelloLists.get(0).getId());
        assertEquals("listDto", trelloLists.get(0).getName());
        assertTrue(trelloLists.get(0).isClosed());
    }

    @Test
    public void shouldMapToListDto() {
        //Given
        List<TrelloListDto> trelloListDtoList = new ArrayList<>();
        TrelloListDto trelloListDto = new TrelloListDto("2", "list", false);
        trelloListDtoList.add(trelloListDto);

        //When
        List<TrelloList> trelloLists = trelloMapper.mapToList(trelloListDtoList);

        //Then
        assertEquals(1, trelloLists.size());
        assertEquals("2", trelloLists.get(0).getId());
        assertEquals("list", trelloLists.get(0).getName());
        assertFalse(trelloLists.get(0).isClosed());
    }

    @Test
    public void shouldMapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("To do", "Tasks to do", "3", "1");

        //When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);

        //Then
        assertEquals("To do", trelloCard.getName());
        assertEquals("Tasks to do", trelloCard.getDescription());
        assertEquals("3", trelloCard.getPos());
        assertEquals("1", trelloCard.getListId());
    }

    @Test
    public void shouldMapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("In progress", "Tasks in progress", "2", "2");

        //When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);

        //Then
        assertEquals("In progress", trelloCardDto.getName());
        assertEquals("Tasks in progress", trelloCardDto.getDescription());
        assertEquals("2", trelloCardDto.getPos());
        assertEquals("2", trelloCardDto.getListId());
    }
}
