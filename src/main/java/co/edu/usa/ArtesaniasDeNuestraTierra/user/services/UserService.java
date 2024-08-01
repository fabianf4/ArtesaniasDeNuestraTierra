package co.edu.usa.ArtesaniasDeNuestraTierra.user.services;

import co.edu.usa.ArtesaniasDeNuestraTierra.user.User;

public interface UserService
{    
	public User createUser(User user) throws Exception;
	
	public User getUser(String phone);
	
	public User deleteUser(User user);
	
	public boolean existsByPhone(String phone);
}