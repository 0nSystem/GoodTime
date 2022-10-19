package com.goodtime.webservice.service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodtime.webservice.data.repository.UserFriendPetitionRepository;
import com.goodtime.webservice.data.vo.UserFriendPetitionVO;
import com.goodtime.webservice.service.dto.UserFriendPetitionDto;
import com.goodtime.webservice.service.interfaces.IUserFriendPetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserFriendPetitionService implements IUserFriendPetitionService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserFriendPetitionRepository userFriendPetitionRepository;

    @Override
    public List<UserFriendPetitionDto> findByReceivedEarringToAcceptAndNotDecline(long idReceived) {
        return Arrays.stream(objectMapper.convertValue(userFriendPetitionRepository.findByUserReceivedToEarringAcceptAndNotDecline(idReceived),
                UserFriendPetitionDto[].class)).toList();
    }

    @Override
    public List<UserFriendPetitionDto> findByUserToEarringAcceptAndNotDecline(long userId) {
        return Arrays.stream(objectMapper.convertValue(userFriendPetitionRepository.findByUserToEarringAcceptAndNotDecline(userId),
                UserFriendPetitionDto[].class)).toList();
    }

    @Override
    public void insertPetition(long idReceived, long idSend) {
        Optional<UserFriendPetitionVO> userFriendPetitionVO=userFriendPetitionRepository.findBySenderAndReceiver(idReceived,idSend);
        if(userFriendPetitionVO.isEmpty()){
            UserFriendPetitionVO userFriendPetition=new UserFriendPetitionVO(idSend,idReceived,false,false);
            userFriendPetitionRepository.save(userFriendPetition);
        }
        /*
        Revisar para ir tirando valido, esto no deberia pasar
         */
        if(userFriendPetitionVO.isPresent()){
            userFriendPetitionVO.get().setDecline(false);
            userFriendPetitionVO.get().setAccepted(false);
            userFriendPetitionRepository.save(userFriendPetitionVO.get());
        }
    }

    @Override
    public void acceptPetition(long idReceived, long idSend) {
        Optional<UserFriendPetitionVO> userFriendPetitionVO=userFriendPetitionRepository.findBySenderAndReceiverToAcceptAndNotDecline(idReceived,idSend);
        if(userFriendPetitionVO.isPresent()){
            if(!userFriendPetitionVO.get().getAccepted()&&!userFriendPetitionVO.get().getDecline()){
                userFriendPetitionVO.get().setAccepted(true);
                userFriendPetitionRepository.save(userFriendPetitionVO.get());
            }
        }
    }

    @Override
    public void declinePetition(long idReceived, long idSend) {
        Optional<UserFriendPetitionVO> userFriendPetitionVO=userFriendPetitionRepository.findBySenderAndReceiver(idReceived,idSend);
        if(userFriendPetitionVO.isPresent()){
            if(!userFriendPetitionVO.get().getDecline()){
                userFriendPetitionVO.get().setDecline(true);
                userFriendPetitionRepository.save(userFriendPetitionVO.get());
            }
        }
    }

}
