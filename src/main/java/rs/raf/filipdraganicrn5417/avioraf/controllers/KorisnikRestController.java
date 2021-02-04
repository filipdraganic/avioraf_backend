package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.Korisnik;
import rs.raf.filipdraganicrn5417.avioraf.model.LoginForm;
import rs.raf.filipdraganicrn5417.avioraf.model.UserType;
import rs.raf.filipdraganicrn5417.avioraf.services.AvionskaKartaService;
import rs.raf.filipdraganicrn5417.avioraf.services.KorisnikService;
import rs.raf.filipdraganicrn5417.avioraf.services.RezervacijaService;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.websocket.server.PathParam;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
@RequestMapping("/api/korisnici")
public class KorisnikRestController {

    private final String salt = "123";
    private final KorisnikService korisnikService;
    private final RezervacijaService rezervacijaService;
    private final AvionskaKartaService avionskaKartaService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public KorisnikRestController(KorisnikService korisnikService, AvionskaKartaService avionskaKartaService, RezervacijaService rezervacijaService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.korisnikService = korisnikService;
        this.rezervacijaService = rezervacijaService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.avionskaKartaService = avionskaKartaService;
    }

    @CrossOrigin
    @GetMapping(value = "/all")
    public List<Korisnik> getAllKorisnik(){
        System.out.println("Get all??");
        return korisnikService.findAll();
    }
    @CrossOrigin
    @GetMapping(value = "/username", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getKorisnikByUsername(@PathParam("username") String username){

        System.out.println("Getting user by username");
        Optional<Korisnik> optionalKorisnik = korisnikService.findByUsername(username);

        System.out.println(optionalKorisnik.get());

        Korisnik korisnik = optionalKorisnik.get();

        return ResponseEntity.ok(korisnik);
    }

    @CrossOrigin
    @GetMapping(value = "/id", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> getKorisnikByIdParam(@PathParam("id") Long id){

        System.out.println("Getting user by username");
        Optional<Korisnik> optionalKorisnik = korisnikService.findById(id);

        System.out.println(optionalKorisnik.get());

        Korisnik korisnik = optionalKorisnik.get();

        return ResponseEntity.ok(korisnik);
    }

    @CrossOrigin
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getKorisnikById(@PathVariable("id") Long id){
        Optional<Korisnik> optionalKorisnik = korisnikService.findById(id);
        if(optionalKorisnik.isPresent()) {
            return ResponseEntity.ok(optionalKorisnik.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> loginKorisnik(@RequestBody LoginForm loginForm){
        System.out.println("Logged try");
        List<Korisnik> korisici = korisnikService.findAll();

        for(Korisnik korisnik : korisici){
            if(korisnik.getUsername() == loginForm.getUsername()){
                if(bCryptPasswordEncoder.matches(loginForm.getPassword(), korisnik.getPassword())){
                    System.out.println("Logged in");
                    return ResponseEntity.ok(korisnik);
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



    @CrossOrigin
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

    @CrossOrigin
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Korisnik updateKorisnik(@RequestBody Korisnik korisnik) {
        return korisnikService.save(korisnik);
    }

    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Korisnik> deleteKorisnik(@PathVariable("id") Long id){
        korisnikService.deleteById(id);
        return ResponseEntity.ok().build();
    }


    @PutMapping(value="/booking", produces = MediaType.APPLICATION_JSON_VALUE)
    public void addBooking(@PathParam("korisnikId") Long korisnikId, @PathParam("bookingId") Long bookingId){
        Optional<Korisnik> korisnik = korisnikService.findById(korisnikId);

        System.out.println("AAAAAAAAAAAAAAAAAAAA");
//        if(korisnik.isPresent()){
//            if (booking.isPresent()){
//                korisnik.get().getBookings().add(booking.get());
//                return ResponseEntity.ok(korisnikService.save(korisnik.get()));
//            } else {
//                return ResponseEntity.notFound().build();
//            }
//        }else{
//            return ResponseEntity.notFound().build();
//        }


    }

    @PutMapping(value="/sifra", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changePassword(@PathParam("password") String password,@PathParam("test")int test){
        System.out.println("test");
        System.out.println("test: " + test);
//        System.out.println("KorisnikId : " + korisnikIdint);
        System.out.println("test");
        Optional<Korisnik> korisnikOptional = korisnikService.findById((long) test);
        if(!korisnikOptional.isPresent()){
            return ResponseEntity.status(400).body("Ne postoji korisnik sa zeljenim IDjem");
        }

        Korisnik korisnik = korisnikOptional.get();
        korisnik.setNoviKolacic(0);
        Pattern pattern = Pattern.compile("^(?=\\D*\\d)\\S{6,}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(password);
        boolean matchFound = matcher.find();

        if(matchFound) {
            korisnik.setPassword(bCryptPasswordEncoder.encode(password));

            if (korisnik.getUserType() == UserType.ADMIN) {
                korisnik.setBookings(null);
            }

            return ResponseEntity.ok(korisnikService.save(korisnik));
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Mora bolji password da bude");
        }

    }

    @CrossOrigin
    @GetMapping("/isAuthenticated")ResponseEntity<?> isAuthenticated(){
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }


}
