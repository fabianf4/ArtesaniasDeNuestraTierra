package co.edu.usa.ArtesaniasDeNuestraTierra.apiHealth;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/health")
public class apiHealthController {
    
    @GetMapping
    public String health(){
        return "Api Rest is working";
    }

    @GetMapping("/secure")
    public String secure(){
        return "Api Rest is working with security";
    }
}
