package com.eder000000.app.ws.shared;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Utils {
    public String generatedUserId(){
        return UUID.randomUUID().toString();
    }
}
