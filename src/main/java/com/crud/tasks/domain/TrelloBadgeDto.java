package com.crud.tasks.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
//@NoArgsConstructor
@AllArgsConstructor
public class TrelloBadgeDto {

    @JsonProperty("votes")
    private int votes;

    @JsonProperty("attachmentsByType")
    private TrelloAttachmentByTypeDto attachmentsByType;
}
