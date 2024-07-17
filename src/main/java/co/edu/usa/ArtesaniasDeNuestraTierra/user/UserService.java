package co.edu.usa.ArtesaniasDeNuestraTierra.user;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

@Service
public class UserService implements UserDetailsService{
    
    @Autowired
    private UserRepository userRepository;

    public User createUser(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStateDelete(false);
        user.setDateCreate(new Date());
        user.setDateUpdate(new Date());
        System.out.println(user.toString());
        return userRepository.save(user);
    }

    public boolean existsByPhone(String phone){
        return userRepository.existsByPhone(phone);
    }

    public User deleteUser(User user){
        user.setStateDelete(true);
        user.setDateUpdate(new Date());
        return userRepository.save(user);
    }
    
    public User getUserAuthenticated(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByPhone(authentication.getName());
    }

    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        User user = userRepository.findByPhone(phone);

        if (user == null || user.isStateDelete()) {
            throw new UsernameNotFoundException("Email not found");
        }

        return org.springframework.security.core.userdetails.User
            .withUsername(user.getPhone())
            .password(user.getPassword())
            .roles()
            .build();
    }
}
