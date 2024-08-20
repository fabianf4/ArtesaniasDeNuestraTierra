package co.edu.usa.ArtesaniasDeNuestraTierra.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regions")
@CrossOrigin("*")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/")
    public List<Region> getAllRegions() {
        return regionService.getAllRegions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getRegionById(@PathVariable int id) {
        Optional<Region> region = regionService.getRegionById(id);
        if (region.isPresent()) {
            return ResponseEntity.ok(region.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}