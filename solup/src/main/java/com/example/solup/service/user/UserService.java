package com.example.solup.service.user;

import com.example.solup.dto.UserDto;
import com.example.solup.entity.User;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto save(UserDto userDto) {
        User user = userRepository.save(userDto.toEnity());
        return user.toDto();
    }

    public UserDto login(UserDto userDto) {
        try{
            User user = userRepository.findByUsername(userDto.getUsername());
            String password = user.getPassword();
            if(userDto.getPassword().equals(password)){
                return user.toDto();
            }else {
                throw new SecurityException();
            }
        }catch(Exception e){
            throw new NoSuchElementException ();
        }

    }
}
