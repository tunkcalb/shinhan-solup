package com.example.solup.service.user;

import com.example.solup.dto.UserDto;
import com.example.solup.entity.User;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto save(UserDto userDto) {
        User user = userRepository.save(userDto.toEnity());
        return user.toDto();
    }
}
