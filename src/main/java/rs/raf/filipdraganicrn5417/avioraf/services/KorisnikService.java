package rs.raf.filipdraganicrn5417.avioraf.services;

import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.Korisnik;
import rs.raf.filipdraganicrn5417.avioraf.model.Rezervacija;
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

    public void removeRezervacija(Rezervacija rezervacija){
        Optional<Korisnik> optionalKorisnik = korisnikRepository.findById(rezervacija.getKorisnik().getId());
        if(optionalKorisnik.isPresent()){
            Korisnik korisnik = optionalKorisnik.get();
            korisnik.getBookings().remove(rezervacija);
            korisnikRepository.save(korisnik);
        }


    }

    public boolean addRezervacija(Rezervacija rezervacija){

        Optional<Korisnik> optionalKorisnik = korisnikRepository.findById(rezervacija.getKorisnik().getId());
        if(optionalKorisnik.isPresent()){
            Korisnik luckyKorisnik = optionalKorisnik.get();
            List<Rezervacija> tempRezervacije = luckyKorisnik.getBookings();
            for (Rezervacija tempRezervacija : tempRezervacije){
                if(tempRezervacija.getId() == rezervacija.getId()){
                    return false;
                }
            }
            System.out.println("Dodavanje rezervacije");
            tempRezervacije.add(rezervacija);
            luckyKorisnik.setBookings(tempRezervacije);
            System.out.println(luckyKorisnik.getBookings());
            korisnikRepository.save(luckyKorisnik);
            System.out.println("Sacuvan korisnik");
            return true;
        }

        return false;
    }
}
