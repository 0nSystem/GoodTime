package com.goodtime.webservice.service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodtime.webservice.data.repository.UserRepository;
import com.goodtime.webservice.service.interfaces.ISessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class SessionService implements ISessionService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public void login(String username, String password) {
        Authentication authentication=authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username,password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Override
    public boolean logout() {
        try{
            SecurityContextHolder.clearContext();
            httpSession.invalidate();
            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
