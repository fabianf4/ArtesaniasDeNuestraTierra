package co.edu.usa.ArtesaniasDeNuestraTierra.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public Optional<Region> getRegionById(int id) {
        return regionRepository.findById(id);
    }

    public Region saveRegion(Region region) {
    	region.setStateDelete(false);
        region.setDateCreate(new Date());
        region.setDateUpdate(new Date());
        return regionRepository.save(region);
    }

    public Region updateRegion(Region region) {
        Optional<Region> existingRegion = regionRepository.findById(region.getId());
        if (existingRegion.isPresent()) {
            Region updatedRegion = existingRegion.get();
            updatedRegion.setCode(region.getCode());
            updatedRegion.setName(region.getName());
            updatedRegion.setStateDelete(region.isStateDelete());
            updatedRegion.setDateCreate(region.getDateCreate());
            updatedRegion.setDateUpdate(region.getDateUpdate());
            return regionRepository.save(updatedRegion);
        } else {
            throw new RuntimeException("Region not found with id " + region.getId());
        }
    }

    public void deleteRegion(int id) {
        regionRepository.deleteById(id);
    }
}