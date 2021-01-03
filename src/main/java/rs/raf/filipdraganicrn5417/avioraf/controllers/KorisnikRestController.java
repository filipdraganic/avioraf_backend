package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.Korisnik;
import rs.raf.filipdraganicrn5417.avioraf.services.KorisnikService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/korisnici")
public class KorisnikRestController {

    private final KorisnikService korisnikService;

    public KorisnikRestController(KorisnikService korisnikService) {
        this.korisnikService = korisnikService;
    }

    @GetMapping(value = "/all")
    public List<Korisnik> getAllKorisnik(){
        return korisnikService.findAll();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getKorisnikById(@RequestParam("korisnikId") Long id){
        Optional<Korisnik> optionalKorisnik = korisnikService.findById(id);
        if(optionalKorisnik.isPresent()) {
            return ResponseEntity.ok(optionalKorisnik.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Korisnik createKorisnik(@RequestBody Korisnik korisnik){
        return  korisnikService.save(korisnik);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Korisnik updateKorisnik(@RequestBody Korisnik korisnik) {
        return korisnikService.save(korisnik);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Korisnik> deleteKorisnik(@PathVariable("id") Long id){
        korisnikService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
