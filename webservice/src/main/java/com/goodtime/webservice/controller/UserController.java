package com.goodtime.webservice.controller;

import com.goodtime.webservice.service.dto.UserDto;
import com.goodtime.webservice.service.services.UserService;
import com.goodtime.webservice.util.LoggerGoodtime;
import com.goodtime.webservice.util.ManagerAttributesSession;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserService userService;
    @Autowired
    private LoggerGoodtime loggerGoodtime;

    @GetMapping("/myinfo")
    public ResponseEntity<?> getMyUserInfo(){
        try{
            ManagerAttributesSession managerAttributesSession=ManagerAttributesSession.getAttributesInHttpSession(httpSession);
            UserDto userDto=userService.findById(managerAttributesSession.getId());
            return new ResponseEntity<>(userDto,HttpStatus.OK);
        }catch (Exception e){
            loggerGoodtime.logInfo(UserController.class,"Error not found users");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}
