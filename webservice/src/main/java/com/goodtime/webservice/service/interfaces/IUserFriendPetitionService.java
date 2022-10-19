package com.goodtime.webservice.service.interfaces;

import com.goodtime.webservice.data.vo.UserFriendPetitionVO;
import com.goodtime.webservice.service.dto.UserFriendPetitionDto;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IUserFriendPetitionService {
    List<UserFriendPetitionDto> findByReceivedEarringToAcceptAndNotDecline(long idReceived);
    List<UserFriendPetitionDto> findByUserToEarringAcceptAndNotDecline(long userId);
    void insertPetition(long idReceived,long idSend);
    void acceptPetition(long idReceived,long idSend);
    void declinePetition(long idReceived,long idSend);

}
