package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.*;
import rs.raf.filipdraganicrn5417.avioraf.services.AvionskaKartaService;
import rs.raf.filipdraganicrn5417.avioraf.services.KorisnikService;
import rs.raf.filipdraganicrn5417.avioraf.services.RezervacijaService;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/rezervacije")
public class RezervacijaRestController {

    private final RezervacijaService rezervacijaService;
    private final KorisnikService korisnikService;
    private final AvionskaKartaService avionskaKartaService;

    public RezervacijaRestController(RezervacijaService rezervacijaService, KorisnikService korisnikService, AvionskaKartaService avionskaKartaService) {
        this.rezervacijaService = rezervacijaService;
        this.korisnikService = korisnikService;
        this.avionskaKartaService = avionskaKartaService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Rezervacija> getAllRezervacija(){
        return rezervacijaService.findAll();
    }

    @GetMapping(value = "/{rezervacijaId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getRezervacijaById(@PathVariable("rezervacijaId") Long id){
        Optional<Rezervacija> optionalRezervacija = rezervacijaService.findById(id);
        if(optionalRezervacija.isPresent()){
            return ResponseEntity.ok(optionalRezervacija.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createRezervacija(@RequestBody RezervacijaDTO rezervacijaDTO){
//        System.out.println("REZERVACIJA = " +rezervacija.getKorisnik().toString());

//        System.out.println(rezervacijaDTO.getAvionskaKarta().toString());
//        System.out.println(rezervacijaDTO.getLet().toString());

        System.out.println(rezervacijaDTO.getKorisnik().toString());

        Rezervacija rezervacija = rezervacijaDTO.getRezervacija();
        rezervacija.setKorisnik(rezervacijaDTO.getKorisnik());

        Rezervacija newRezervacija = rezervacijaService.save(rezervacija);
//        Boolean ishod = korisnikService.addRezervacija(newRezervacija);
//        if(ishod){
//            AvionskaKarta karta = newRezervacija.getAvionskaKarta();
//            avionskaKartaService.reduceCount(karta, 1);
        avionskaKartaService.reduceCount(newRezervacija.getAvionskaKarta(), 1);
            return ResponseEntity.ok(newRezervacija);
//        }else{
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok("OK");
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Rezervacija updateRezervacija(@RequestBody Rezervacija rezervacija){
        return rezervacijaService.save(rezervacija);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteRezervacija(@PathVariable("id") Long id){
        Optional<Rezervacija> optionalRezervacija = rezervacijaService.findById(id);
        if(optionalRezervacija.isPresent()){
            Rezervacija rezervacija = optionalRezervacija.get();

            korisnikService.removeRezervacija(rezervacija);
        }
        rezervacijaService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getRezervacijeOfKorisnik(@PathParam("korisnikId") long korisnikId){
        return ResponseEntity.ok(rezervacijaService.getRezervacijeByKorisnikId(korisnikId));
    }

}
