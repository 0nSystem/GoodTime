package com.goodtime.webservice.controller;


import com.goodtime.webservice.service.dto.UserRegisterDto;
import com.goodtime.webservice.service.interfaces.ISessionService;
import com.goodtime.webservice.service.interfaces.IUserService;
import com.goodtime.webservice.util.LoggerGoodtime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    @Autowired
    private ISessionService sessionService;
    @Autowired
    private IUserService userService;
    @Autowired
    private LoggerGoodtime loggerGoodtime;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam  String password){
        try {
            sessionService.login(username,password);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
           loggerGoodtime.logInfo(AuthenticationController.class,"Cant Authenticate user");
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/logout")
    public ResponseEntity<?> logout(){
        try {
            boolean isLogout= sessionService.logout();
            if(!isLogout){
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            loggerGoodtime.logInfo(AuthenticationController.class,"cant logout");
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserRegisterDto userRegisterDto){
        try {
            if(userService.insert(userRegisterDto))
                return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            loggerGoodtime.logInfo(AuthenticationController.class,"cant register user with username is duplicate");
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
