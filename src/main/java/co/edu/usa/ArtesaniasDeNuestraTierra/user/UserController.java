package co.edu.usa.ArtesaniasDeNuestraTierra.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;
   
    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody @Valid User user){
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
   
}
