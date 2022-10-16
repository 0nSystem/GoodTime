package com.goodtime.webservice.util;

import org.springframework.stereotype.Service;

import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class LoggerGoodtime {
    private final Logger logger= Logger.getLogger(LoggerGoodtime.class.getName());

    public void logError(Class<?> aClass,String body){
        logger.log(Level.WARNING,String.format("%s - %s",aClass.getName(),body));
    }
    public void logInfo(Class<?> aClass,String body){
        logger.log(Level.INFO,String.format("%s - %s",aClass.getName(),body));
    }
}
