package co.edu.usa.ArtesaniasDeNuestraTierra.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.usa.ArtesaniasDeNuestraTierra.config.JwtUtils;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.User;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.services.UserDetailsServiceImpl;

@RestController
@CrossOrigin("*")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsServiceImpl;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            auth(jwtRequest.getPhone(), jwtRequest.getPassword());
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails = this.userDetailsServiceImpl.loadUserByUsername(jwtRequest.getPhone());
        User user 				= (User) userDetails;
        String token 			= this.jwtUtils.generateToken(userDetails);

        JwtResponse jwtResponse = new JwtResponse(token, user.getPhone(), user.getId(), user.getName());
        return ResponseEntity.ok(jwtResponse);
    }
	
	private void auth(String phone, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phone, password));
        } catch (DisabledException disabledException) {
            throw new Exception("USUARIO DESHABILITADO: " + disabledException.getMessage());
        } catch (BadCredentialsException badCredentialsException) {
            throw new Exception("Credenciales invalidas: " + badCredentialsException.getMessage());
        }
    }
}
