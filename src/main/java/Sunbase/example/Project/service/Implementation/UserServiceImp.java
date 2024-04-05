package Sunbase.example.Project.service.Implementation;

import Sunbase.example.Project.customExceptions.UserNotFoundException;
import Sunbase.example.Project.dto.request.UserRequestDto;
import Sunbase.example.Project.dto.response.UserResponseDto;
import Sunbase.example.Project.models.User;
import Sunbase.example.Project.repository.UserRepository;
import Sunbase.example.Project.service.UserService;
import Sunbase.example.Project.transformer.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserResponseDto addUser(UserRequestDto userRequestDto) {
        User user = UserTransformer.userFromUserRequestDto(userRequestDto);

        User savedUser = userRepository.save(user);

        UserResponseDto userResponseDto = UserTransformer.userResponseDtoFromUser(savedUser);
        userResponseDto.setMessage("User Has Been Added to the Db Successfully!!");

        return userResponseDto;
    }

    @Override
    public UserResponseDto updateUser(int id, UserRequestDto userRequestDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            UserResponseDto dto = addUser(userRequestDto);
            dto.setMessage("We Didn't Found Anything Related to this Id so. We have created Your Account");
            return dto;
        }

        User user = optionalUser.get();
        user = UserTransformer.userFromUserDto(user, userRequestDto);

        User savedUser = userRepository.save(user);

        UserResponseDto responseDto = UserTransformer.userResponseDtoFromUser(savedUser);
        responseDto.setMessage("User With name:" + user.getFirstName() + " has been Updated");
        return responseDto;
    }

    @Override
    public UserResponseDto deleteUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new UserNotFoundException("User with id " + id + " is Not Found");
        userRepository.deleteById(id);
        User user = optionalUser.get();
        UserResponseDto responseDto = UserTransformer.userResponseDtoFromUser(user);
        responseDto.setMessage("User with id:" + user.getId() + " has been deleted Successfully!!");
        return responseDto;
    }

    @Override
    public List<UserResponseDto> getUsersBy(String search, String value) {
        List<User> userList;
        switch (search) {
            case "city": {
                userList = userRepository.findByCity(value);
                break;
            }
            case "phone": {
                userList = userRepository.findByPhone(value);
                break;
            }
            case "first": {
                userList = userRepository.findByFirstName(value);
                break;
            }
            case "email": {
                userList = userRepository.findByEmail(value);
                break;
            }
            default: {
                userList=userRepository.findAll();
            }
        }

        List<UserResponseDto> userResponseDtos = userList.stream()
                .map(ele -> UserTransformer.userResponseDtoFromUser(ele))
                .collect(Collectors.toList());

        return userResponseDtos;
    }


    @Override
    public UserResponseDto getUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) throw new UserNotFoundException("Unable Find User with userId:" + id);

        User user = optionalUser.get();
        UserResponseDto dto = UserTransformer.userResponseDtoFromUser(user);
        dto.setMessage("User is Found");
        return dto;
    }
}
