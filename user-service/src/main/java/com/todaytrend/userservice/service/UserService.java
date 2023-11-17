package com.todaytrend.userservice.service;

import com.todaytrend.userservice.domain.User;
import com.todaytrend.userservice.dto.RequestCreateUserDto;
import com.todaytrend.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(RequestCreateUserDto requestCreateUserDto){
        User user = requestCreateUserDto.toEntity();
        userRepository.save(user);
    }
}