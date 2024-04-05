package Sunbase.example.Project.controller;

import Sunbase.example.Project.dto.request.UserRequestDto;
import Sunbase.example.Project.dto.response.ExceptionResponseDto;
import Sunbase.example.Project.dto.response.UserResponseDto;
import Sunbase.example.Project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public ResponseEntity createUser(@RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto responseDto = userService.addUser(userRequestDto);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestParam("id") int id, @RequestBody UserRequestDto userRequestDto) {
        try {
            UserResponseDto userResponse = userService.updateUser(id, userRequestDto);

            return new ResponseEntity(userResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam("id") int userId) {
        try {
            UserResponseDto userResponseDto = userService.deleteUser(userId);
            return new ResponseEntity(userResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find-by-search/{search}")
    public ResponseEntity findBySearch(@PathVariable("search") String search,@RequestParam("value") String value) {
        try {
            List<UserResponseDto> responseDto = userService.getUsersBy(search,value);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity findById(@RequestParam("id")int userId){
        try{
            UserResponseDto userResponseDto=userService.getUser(userId);
            return new ResponseEntity(userResponseDto,HttpStatus.FOUND);
        }catch (Exception e){
            return new ResponseEntity(new ExceptionResponseDto(e.getMessage()),HttpStatus.NOT_FOUND);
        }
    }
}
