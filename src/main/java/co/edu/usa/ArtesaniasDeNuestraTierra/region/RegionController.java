package co.edu.usa.ArtesaniasDeNuestraTierra.region;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
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

    @PostMapping
    public Region saveRegion(@RequestBody Region region) {
        return regionService.saveRegion(region);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(@PathVariable int id, @RequestBody Region region) {
        region.setId(id);
        try {
            return ResponseEntity.ok(regionService.updateRegion(region));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRegion(@PathVariable int id) {
        regionService.deleteRegion(id);
        return ResponseEntity.noContent().build();
    }
}