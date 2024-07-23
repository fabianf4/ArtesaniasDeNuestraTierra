package co.edu.usa.ArtesaniasDeNuestraTierra.city;

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

    public City saveCity(City city)
    {
        return cityRepository.save(city);
    }

    public City updateCity(City city) {
        Optional<City> existingCity = cityRepository.findById(city.getId());
        if (existingCity.isPresent()) {
            City updatedCity = existingCity.get();
            updatedCity.setName(city.getName());
            updatedCity.setStateDelete(city.isStateDelete());
            updatedCity.setDateCreate(city.getDateCreate());
            updatedCity.setDateUpdate(city.getDateUpdate());
            updatedCity.setRegion(city.getRegion());
            return cityRepository.save(updatedCity);
        } else {
            throw new RuntimeException("City not found with id " + city.getId());
        }
    }

    public void deleteCity(int id)
    {
        cityRepository.deleteById(id);
    }
}
