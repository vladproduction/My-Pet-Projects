package com.app.vp.wookiebooks.controller.utils;

import com.app.vp.wookiebooks.dto.UserDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import static com.app.vp.wookiebooks.controller.utils.UserRestClient.findAllUsers;

public class Utils {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static String toJson(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    //for trying start container:
    public static void verifyStart(Long rate, int retries){
        for (int i = 0; i < retries; i++) {
            try {
                List<UserDto> userDtoList = findAllUsers();
                return;
            }catch (Exception e){
                try {
                    Thread.sleep(rate);
                } catch (InterruptedException ex) {
                    //if sleep smt wrong
                    throw new RuntimeException(ex);
                }
            }
        }
    }

}
