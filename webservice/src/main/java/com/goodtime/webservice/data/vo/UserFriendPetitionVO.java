package com.goodtime.webservice.data.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_friend_petition")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserFriendPetitionVO {

    @Id
    @NonNull
    private Long idUserSend;
    @NonNull
    private Long idUserReceived;
    private Boolean accepted;
    private Boolean decline;
}