package rs.raf.filipdraganicrn5417.avioraf.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.raf.filipdraganicrn5417.avioraf.model.Let;
import rs.raf.filipdraganicrn5417.avioraf.services.LetService;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/letovi")
public class LetRestController {

    private final LetService letService;

    public LetRestController(LetService letService) {
        this.letService = letService;
    }

    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Let> getAllLet(){
        return letService.findAll();
    }

    @GetMapping(value = "{letId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getLetById(@PathVariable("letId") Long id){
        Optional<Let> optionalLet = letService.findById(id);
        if(optionalLet.isPresent()){
            return ResponseEntity.ok(optionalLet.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Let createLet(@RequestBody Let let){
        return letService.save(let);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Let updateLet(@RequestBody Let let){
        return letService.save(let);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteLet(@PathVariable("id") Long id){
        letService.deleteById(id);
        return ResponseEntity.ok().build();
    }




}
