package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.Rezervacija;
import rs.raf.filipdraganicrn5417.avioraf.services.RezervacijaService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/rezervacije")
public class RezervacijaRestController {

    private final RezervacijaService rezervacijaService;

    public RezervacijaRestController(RezervacijaService rezervacijaService) {
        this.rezervacijaService = rezervacijaService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Rezervacija> getAllRezervacija(){
        return rezervacijaService.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRezervacijaById(@RequestParam("rezervacijaId") Long id){
        Optional<Rezervacija> optionalRezervacija = rezervacijaService.findById(id);
        if(optionalRezervacija.isPresent()){
            return ResponseEntity.ok(optionalRezervacija.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Rezervacija createRezervacija(@RequestBody Rezervacija rezervacija){
        return rezervacijaService.save(rezervacija);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Rezervacija updateRezervacija(@RequestBody Rezervacija rezervacija){
        return rezervacijaService.save(rezervacija);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRezervacija(@PathVariable("id") Long id){
        rezervacijaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
