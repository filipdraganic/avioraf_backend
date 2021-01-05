package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.Korisnik;
import rs.raf.filipdraganicrn5417.avioraf.model.LoginForm;
import rs.raf.filipdraganicrn5417.avioraf.model.Rezervacija;
import rs.raf.filipdraganicrn5417.avioraf.model.UserType;
import rs.raf.filipdraganicrn5417.avioraf.services.KorisnikService;
import rs.raf.filipdraganicrn5417.avioraf.services.RezervacijaService;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.xml.ws.Response;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
@RequestMapping("/api/korisnici")
public class KorisnikRestController {

    private final String salt = "123";
    private final KorisnikService korisnikService;
    private final RezervacijaService rezervacijaService;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public KorisnikRestController(KorisnikService korisnikService, RezervacijaService rezervacijaService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.korisnikService = korisnikService;
        this.rezervacijaService = rezervacijaService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @GetMapping(value = "/all")
    public List<Korisnik> getAllKorisnik(){
        System.out.println("Get all??");
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

    @PostMapping(value = "/login")
    public ResponseEntity<?> loginKorisnik(@RequestBody LoginForm loginForm){

        List<Korisnik> korisici = korisnikService.findAll();

        for(Korisnik korisnik : korisici){
            if(korisnik.getUsername() == loginForm.getUsername()){
                if(bCryptPasswordEncoder.matches(loginForm.getPassword(), korisnik.getPassword())){
                    return ResponseEntity.status(HttpStatus.OK).body("Uspesan login");
                }
                else{
                    return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los password");

                }
            }else{
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los username");

            }

        }
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nema korisnika");

    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createKorisnik(@RequestBody Korisnik korisnik){
        System.out.println("Ovde?");
        String sifra = korisnik.getPassword();
        Pattern pattern = Pattern.compile("^(?=\\D*\\d)\\S{6,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(sifra);
        boolean matchFound = matcher.find();

        if(matchFound) {
            korisnik.setPassword(bCryptPasswordEncoder.encode(korisnik.getPassword()));

            if (korisnik.getUserType() == UserType.ADMIN) {
                korisnik.setBookings(null);
            }

            return ResponseEntity.ok(korisnikService.save(korisnik));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mora bolji password da bude");
        }

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

    @PutMapping(value="/{korisnikId}/{bookingId}")
    public ResponseEntity<Korisnik> addBooking(@RequestParam("korisnikId") Long korisnikId, @RequestParam("bookingId") Long bookingId){
        Optional<Korisnik> korisnik = korisnikService.findById(korisnikId);
        Optional<Rezervacija> booking = rezervacijaService.findById(bookingId);

        if(korisnik.isPresent()){
            if (booking.isPresent()){
                korisnik.get().getBookings().add(booking.get());
                return ResponseEntity.ok(korisnikService.save(korisnik.get()));
            } else {
                return ResponseEntity.notFound().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }


    }

}