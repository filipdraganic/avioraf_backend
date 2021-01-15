package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.AvionskaKompanija;
import rs.raf.filipdraganicrn5417.avioraf.services.AvionskaKompanijaService;

import javax.xml.ws.Response;
import java.util.List;
import java.util.Optional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.Pattern;

@CrossOrigin
@RestController
@RequestMapping("/api/kompanije")
public class AvionskaKompanijaRestController {

    private final AvionskaKompanijaService avionskaKompanijaService;

    public AvionskaKompanijaRestController(AvionskaKompanijaService avionskaKompanijaService) {
        this.avionskaKompanijaService = avionskaKompanijaService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AvionskaKompanija> getAllAvionskaKompanija(){
        return avionskaKompanijaService.findAll();
    }

    @GetMapping(value = "{kompanijaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAvionskaKompanijaById(@PathVariable("kompanijaId") Long id){
        Optional<AvionskaKompanija> optionalAvionskaKompanija = avionskaKompanijaService.findById(id);
        if(optionalAvionskaKompanija.isPresent()){
            return ResponseEntity.ok(optionalAvionskaKompanija.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AvionskaKompanija> createAvionskaKompanija(@RequestBody AvionskaKompanija avionskaKompanija){

        String ime = avionskaKompanija .getName();
        Pattern pattern = Pattern.compile("[a-zA-Z0-9]{2,}",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(ime);
        boolean matchFound = matcher.find();
        if(matchFound){
            return ResponseEntity.ok(avionskaKompanijaService.save(avionskaKompanija));
        }else{
            return ResponseEntity.notFound().build();
        }
        }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AvionskaKompanija updateAvionskaKompanija(@RequestBody AvionskaKompanija avionskaKompanija){
        return avionskaKompanijaService.save(avionskaKompanija);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAvionskaKompanija(@PathVariable("id")Long id){
        avionskaKompanijaService.deleteById(id);
        return ResponseEntity.ok().build();
    }





}
