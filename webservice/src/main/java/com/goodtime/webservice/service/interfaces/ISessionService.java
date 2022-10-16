package com.goodtime.webservice.service.interfaces;

public interface ISessionService {
    void login(String username,String password);
    boolean logout();
}
