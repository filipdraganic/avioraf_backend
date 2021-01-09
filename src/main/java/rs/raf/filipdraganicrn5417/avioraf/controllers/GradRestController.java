package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationEntryPoint;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.Grad;
import rs.raf.filipdraganicrn5417.avioraf.services.GradService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/gradovi")
public class GradRestController {

    private final GradService gradService;

    public GradRestController(GradService gradService) {
        this.gradService = gradService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Grad> getAllGrad(){
        return gradService.findAll();
    }

    @GetMapping(value = "{gradId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getGradById(@PathVariable("gradId") Long id){

        Optional<Grad> optionalGrad = gradService.findById(id);
        if(optionalGrad.isPresent()){
            return ResponseEntity.ok(optionalGrad.get());
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Grad updateGrad(@RequestBody Grad grad){
        return gradService.save(grad);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteGrad(@PathVariable("id") Long id){
        gradService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
