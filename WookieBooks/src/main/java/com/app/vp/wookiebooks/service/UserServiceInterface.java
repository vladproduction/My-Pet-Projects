package com.app.vp.wookiebooks.service;

import com.app.vp.wookiebooks.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserServiceInterface {

    void deleteUserByUserId(Long userId) throws UserNotFoundException;


}
