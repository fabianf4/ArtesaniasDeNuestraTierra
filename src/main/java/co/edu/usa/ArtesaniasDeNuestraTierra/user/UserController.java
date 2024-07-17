package co.edu.usa.ArtesaniasDeNuestraTierra.user;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.dto.UserMeDTO;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.dto.UserSignupDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
   
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody @Valid UserSignupDTO user){

        User newUser = User.builder()
            .phone(user.getPhone())
            .password(user.getPassword())
            .name(user.getName())
            .build();

        if (userService.existsByPhone(newUser.getPhone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        
        try {
            newUser = userService.createUser(newUser);

            return ResponseEntity.created(new URI("/api/user/" + newUser.getId())).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserMeDTO> me(){
        
        User user = userService.getUserAuthenticated();

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        UserMeDTO userResponse = UserMeDTO.builder()
            .id(user.getId())
            .phone(user.getPhone())
            .password(user.getPassword() != null ? true : false)
            .name(user.getName())
            .build();

        return ResponseEntity.ok(userResponse);
    }
   
    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteUser(){
        
        User userDelete = userService.getUserAuthenticated();

        if (userDelete == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        userService.deleteUser(userDelete);
        
        return ResponseEntity.ok().build();
    }
}
