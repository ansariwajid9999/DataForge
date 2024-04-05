package Sunbase.example.Project.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    int id;
    String firstName;
    String lastName;
    String address;
    String city;
    String state;
    String email;
    String phone;
    String message;
    String street;
}
