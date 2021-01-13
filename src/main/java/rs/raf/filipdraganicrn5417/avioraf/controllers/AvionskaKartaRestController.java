package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.AvionskaKarta;
import rs.raf.filipdraganicrn5417.avioraf.repositories.AvionskaKartaRepository;
import rs.raf.filipdraganicrn5417.avioraf.services.AvionskaKartaService;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/karte")
public class AvionskaKartaRestController {

    private final AvionskaKartaService avionskaKartaService;

    public AvionskaKartaRestController (AvionskaKartaService avionskaKartaService){
        this.avionskaKartaService = avionskaKartaService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AvionskaKarta> getAllAvionskaKarta(){
        System.out.println(avionskaKartaService.findAll());
        return avionskaKartaService.findAll();}

    @GetMapping(value="{kartaId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAvionskaKartaById(@PathVariable("kartaId") Long id){
        Optional<AvionskaKarta> optionalAvionskaKarta = avionskaKartaService.findById(id);
        if(optionalAvionskaKarta.isPresent()){
            return ResponseEntity.ok(optionalAvionskaKarta.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AvionskaKarta createAvionskaKarta(@RequestBody AvionskaKarta avionskaKarta){
        return avionskaKartaService.save(avionskaKarta);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AvionskaKarta updateAvionskaKarta(@RequestBody AvionskaKarta avionskaKarta){
        return avionskaKartaService.save(avionskaKarta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAvionskaKarta(@PathVariable("id") Long id){
        avionskaKartaService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
