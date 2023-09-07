package com.example.solup.service.user;

import com.example.solup.dto.UserDto;
import com.example.solup.entity.User;
import com.example.solup.exception.type.DuplicatedValueException;
import com.example.solup.exception.type.NotSameDataValueException;
import com.example.solup.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserDto save(UserDto userDto) throws DuplicatedValueException {
        if (userRepository.findByUsername(userDto.getUsername()) == null) {
            return userRepository.save(userDto.toEnity()).toDto();
        }
        throw new DuplicatedValueException("이미 존재하는 아이디입니다");
    }

    public UserDto login(UserDto userDto) throws NotSameDataValueException {
        User user = userRepository.findByUsername(userDto.getUsername());
        if(user == null) {
            throw new NotSameDataValueException("존재하지 않는 ID입니다");
        }
        String password = user.getPassword();
        if (!userDto.getPassword().equals(password)) {
            throw new NotSameDataValueException("비밀번호가 일치하지 않습니다.");

        }
        return user.toDto();
    }
        public String findByUsername (String username){
            if (userRepository.findByUsername(username) != null) return "이미 존재하는 ID입니다";
            return "사용 가능한 ID입니다";
        }
    }
