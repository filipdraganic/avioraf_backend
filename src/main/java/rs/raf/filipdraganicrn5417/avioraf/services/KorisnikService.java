package rs.raf.filipdraganicrn5417.avioraf.services;

import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.Korisnik;
import rs.raf.filipdraganicrn5417.avioraf.repositories.KorisnikRepository;

import java.util.List;
import java.util.Optional;

@Service
public class KorisnikService implements IService<Korisnik, Long>{

    private final KorisnikRepository korisnikRepository;

    public KorisnikService(KorisnikRepository korisnikRepository){
        this.korisnikRepository = korisnikRepository;
    }


    @Override
    public Korisnik save(Korisnik korisnik) {
        return korisnikRepository.save(korisnik);
    }

    @Override
    public Optional<Korisnik> findById(Long id) {
        return korisnikRepository.findById(id);
    }

    @Override
    public List<Korisnik> findAll() {
        return (List<Korisnik>) korisnikRepository.findAll();
    }

    public Optional<Korisnik> findByUsername(String username){
        List<Korisnik> korisnikList = (List<Korisnik>) korisnikRepository.findAll();
        Optional<Korisnik> optionalKorisnik = Optional.empty();
        for(Korisnik korisnik : korisnikList){
            if(korisnik.getUsername().equals(username)){
                optionalKorisnik = Optional.of(korisnik);
                return optionalKorisnik;
            }
        }
        return optionalKorisnik;
    }

    @Override
    public void deleteById(Long id) {
        korisnikRepository.deleteById(id);
    }
}
