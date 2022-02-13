package com.crud.tasks.trello.domain;

import com.crud.tasks.domain.TrelloAttachmentByTypeDto;
import com.crud.tasks.domain.TrelloBadgeDto;
import com.crud.tasks.domain.TrelloTrelloDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TrelloBadgeTest {

    @Mock
    TrelloBadgeDto trelloBadgeDto;

    @Mock
    TrelloTrelloDto trelloTrelloDto;

    @Mock
    TrelloAttachmentByTypeDto trelloAttachmentByTypeDto;

    @Test
    public void checkTrelloBadge() {
        //Given
        TrelloTrelloDto trelloTrelloDto1 = new TrelloTrelloDto(1,19);
        TrelloAttachmentByTypeDto trelloAttachmentByTypeDto1 = new TrelloAttachmentByTypeDto(trelloTrelloDto1);
        TrelloBadgeDto trelloBadgeDto1 = new TrelloBadgeDto(11, trelloAttachmentByTypeDto1);

        //When
        when(trelloBadgeDto.getAttachmentsByType()).thenReturn(trelloAttachmentByTypeDto1);
        when(trelloBadgeDto.getVotes()).thenReturn(11);
        when(trelloAttachmentByTypeDto.getTrello()).thenReturn(trelloTrelloDto1);
        when(trelloTrelloDto.getBoard()).thenReturn(1);
        when(trelloTrelloDto.getCard()).thenReturn(19);

        //Then
        assertThat(trelloBadgeDto.getVotes()).isEqualTo(11);
        assertThat(trelloBadgeDto.getAttachmentsByType()).isEqualTo(trelloAttachmentByTypeDto1);
        assertThat(trelloAttachmentByTypeDto.getTrello()).isEqualTo(trelloTrelloDto1);
        assertThat(trelloTrelloDto.getBoard()).isEqualTo(1);
        assertThat(trelloTrelloDto.getCard()).isEqualTo(19);
    }


}
