package co.edu.usa.ArtesaniasDeNuestraTierra.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
   User findByPhone(String phone); 
   public User findByUsername(String username);
   boolean existsByPhone(String phone);
}
