package co.edu.usa.ArtesaniasDeNuestraTierra.user.services;

import java.util.Date;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import co.edu.usa.ArtesaniasDeNuestraTierra.user.User;
import co.edu.usa.ArtesaniasDeNuestraTierra.user.UserRepository;

@Service
public class UserServiceImpl implements UserService
{
	@Autowired
	private UserRepository userRepository;

	@Override
	public User createUser(User user) throws Exception {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setStateDelete(false);
        user.setDateCreate(new Date());
        user.setDateUpdate(new Date());
        System.out.println(user.toString());
        return userRepository.save(user);
	}

	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
    public User deleteUser(User user){
        user.setStateDelete(true);
        user.setDateUpdate(new Date());
        return userRepository.save(user);
		
	}

	@Override
	public boolean existsByPhone(String phone) {
        return userRepository.existsByPhone(phone);
	}

	@Override
	public User getPhone(String phone) {
		return userRepository.findByPhone(phone);
	}

}
