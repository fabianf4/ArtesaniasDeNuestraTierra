package co.edu.usa.ArtesaniasDeNuestraTierra.user;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usa.ArtesaniasDeNuestraTierra.user.dto.UserSignupDTO;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;
   
    @PostMapping("/signup")
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserSignupDTO userSignupDTO) throws Exception {    	
    	// Verificar si el telefono ya existe
        if (userService.getUser(userSignupDTO.getPhone()) != null) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "El telefono ya existe");
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
        }

    	User newUser = new User();
        newUser.setPassword(userSignupDTO.getPassword());
        newUser.setName(userSignupDTO.getName());
        newUser.setPhone(userSignupDTO.getPhone());

        // Crear el nuevo usuario
        User createdUser = userService.createUser(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
    
    @GetMapping("/{phone}")
	public User getUser(@PathVariable("phone") String phone)
	{
		return userService.getUser(phone);
	}
}
