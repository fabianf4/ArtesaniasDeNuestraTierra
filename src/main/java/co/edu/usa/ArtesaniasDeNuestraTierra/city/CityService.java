package co.edu.usa.ArtesaniasDeNuestraTierra.city;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService
{
	@Autowired
    private CityRepository cityRepository;

    public List<City> getAllCities()
    {
        return cityRepository.findAll();
    }

    public Optional<City> getCityById(int id)
    {
        return cityRepository.findById(id);
    }
    
    public List<City> getCitiesByRegionId(int regionId) {
        return cityRepository.findByRegionId(regionId);
    }
}
