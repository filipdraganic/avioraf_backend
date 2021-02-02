package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.AvionskaKarta;
import rs.raf.filipdraganicrn5417.avioraf.model.PageDTO;
import rs.raf.filipdraganicrn5417.avioraf.repositories.AvionskaKartaRepository;
import rs.raf.filipdraganicrn5417.avioraf.services.AvionskaKartaService;

import javax.print.attribute.standard.Media;
import javax.websocket.server.PathParam;
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
    public ResponseEntity<?> getAllAvionskaKarta(@PathParam("currentPage")Integer currentPage){
//        System.out.println(currentPage);
        if (currentPage == null) currentPage = 1;

        System.out.println("Current Page: " + currentPage);

        PageDTO pageDTO = new PageDTO();


        System.out.println(avionskaKartaService.findAll(currentPage-1,5));
        System.out.println(avionskaKartaService.findAll(currentPage-1, 5).toList());


        pageDTO.setAvionskaKartaList(avionskaKartaService.findAll(currentPage-1,5).toList());
        pageDTO.setSize(avionskaKartaService.findAll().size());

        return new ResponseEntity<PageDTO>(pageDTO, HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getAvionskaKartaById(@PathParam("id") Long id){
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AvionskaKarta updateAvionskaKarta(@RequestBody AvionskaKarta avionskaKarta){
        System.out.println("Editovanje karte in progress, poslata karta:");
        System.out.println(avionskaKarta.toString() );
        Optional<AvionskaKarta> optionalAvionskaKarta = avionskaKartaService.findById(avionskaKarta.getId());

        System.out.println("Iz baze:");
        System.out.println(optionalAvionskaKarta.get().toString());

        return avionskaKartaService.save(avionskaKarta);
    }

    @DeleteMapping(value = {"/{id}"})
    public ResponseEntity<?> deleteAvionskaKarta(@PathVariable("id") Long id){
        System.out.println("Brisanje karte in progress");

        avionskaKartaService.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
