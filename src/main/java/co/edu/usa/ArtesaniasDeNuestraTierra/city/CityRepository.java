package co.edu.usa.ArtesaniasDeNuestraTierra.city;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer>
{
	List<City> findByRegions_Id(int regionId);
}
