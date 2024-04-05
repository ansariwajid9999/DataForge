package Sunbase.example.Project.service;

import Sunbase.example.Project.dto.request.UserRequestDto;
import Sunbase.example.Project.dto.response.UserResponseDto;

import java.util.List;

public interface UserService {

    UserResponseDto addUser(UserRequestDto userRequestDto);

    UserResponseDto updateUser(int id,UserRequestDto userRequestDto);

    UserResponseDto deleteUser(int id);

    UserResponseDto getUser(int id);

    List<UserResponseDto> getUsersBy(String search, String value);
}
