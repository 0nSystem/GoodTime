package com.goodtime.webservice.controller;

import com.goodtime.webservice.service.interfaces.IUserFriendPetitionService;
import com.goodtime.webservice.util.LoggerGoodtime;
import com.goodtime.webservice.util.ManagerAttributesSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/user/friend")
public class UserFriendPetitionController {
    @Autowired
    private LoggerGoodtime loggerGoodtime;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private IUserFriendPetitionService iUserFriendPetitionService;

    @GetMapping("/getReceivedEarringToAccept")
    public ResponseEntity<?> getReceivedEarringToAccept(){
        try{
            ManagerAttributesSession managerAttributesSession=ManagerAttributesSession.getAttributesInHttpSession(httpSession);
            return new ResponseEntity<>(iUserFriendPetitionService.findByReceivedEarringToAcceptAndNotDecline(managerAttributesSession.getId()),HttpStatus.OK);
        }catch (Exception e){
            loggerGoodtime.logInfo(UserFriendPetitionController.class,"Error getReceivedEarringToAccept");
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/getAllToEarringAcceptAndNotDecline")
    public ResponseEntity<?> getAllToEarringAcceptAndNotDecline(){
        try{
            ManagerAttributesSession managerAttributesSession=ManagerAttributesSession.getAttributesInHttpSession(httpSession);
            return new ResponseEntity<>(iUserFriendPetitionService.findByUserToEarringAcceptAndNotDecline(managerAttributesSession.getId()),HttpStatus.OK);
        }catch (Exception e){
            loggerGoodtime.logInfo(UserFriendPetitionController.class,"Error getAllToEarringAcceptAndNotDecline");
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("/sendPetitionToUser")
    public ResponseEntity<?> sendPetitionToUser(@Param("idUserReceived") long idUserReceived){
        try{
            ManagerAttributesSession managerAttributesSession=ManagerAttributesSession.getAttributesInHttpSession(httpSession);
            iUserFriendPetitionService.insertPetition(managerAttributesSession.getId(), idUserReceived);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            loggerGoodtime.logInfo(UserFriendPetitionController.class,"Error sendPetitionToUser");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/acceptPetition")
    public ResponseEntity<?> acceptPetitionFriend(@Param("idUserSend") long idUserSend){
        try{
            ManagerAttributesSession managerAttributesSession=ManagerAttributesSession.getAttributesInHttpSession(httpSession);
            iUserFriendPetitionService.acceptPetition(managerAttributesSession.getId(), idUserSend);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            loggerGoodtime.logInfo(UserFriendPetitionController.class,"Error acceptPetition");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/declinePetition")
    public ResponseEntity<?> declinePetition(@Param("idUserSend") long idUserSend){
        try{
            ManagerAttributesSession managerAttributesSession=ManagerAttributesSession.getAttributesInHttpSession(httpSession);
            iUserFriendPetitionService.declinePetition(managerAttributesSession.getId(), idUserSend);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            loggerGoodtime.logInfo(UserFriendPetitionController.class,"Error declinePetition");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
