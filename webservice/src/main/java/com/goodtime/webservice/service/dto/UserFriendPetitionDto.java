package com.goodtime.webservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
public class UserFriendPetitionDto {
    private Long idUserSend;
    private Long idUserReceived;
    private boolean accepted;
    private boolean decline;
}

