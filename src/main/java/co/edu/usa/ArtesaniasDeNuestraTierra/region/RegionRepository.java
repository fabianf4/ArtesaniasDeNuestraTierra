package co.edu.usa.ArtesaniasDeNuestraTierra.region;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionRepository extends JpaRepository<Region, Integer>
{
	
}