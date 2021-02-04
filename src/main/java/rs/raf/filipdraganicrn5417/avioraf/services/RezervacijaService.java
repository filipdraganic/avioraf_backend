package rs.raf.filipdraganicrn5417.avioraf.services;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.Rezervacija;
import rs.raf.filipdraganicrn5417.avioraf.repositories.RezervacijaRepository;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@EnableScheduling
@Service
public class RezervacijaService implements IService<Rezervacija, Long>{

    private final RezervacijaRepository rezervacijaRepository;

    public RezervacijaService(RezervacijaRepository rezervacijaRepository){
        this.rezervacijaRepository = rezervacijaRepository;
    }

    @Override
    public Rezervacija save(Rezervacija rezervacija) {
        return rezervacijaRepository.save(rezervacija);
    }

    @Override
    public Optional<Rezervacija> findById(Long id) {
        return rezervacijaRepository.findById(id);
    }

    @Override
    public List<Rezervacija> findAll() {
        return (List<Rezervacija>) rezervacijaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        rezervacijaRepository.deleteById(id);
    }

    public List<Rezervacija> getRezervacijeByKorisnikId(Long id){
        return rezervacijaRepository.getRezervacijaByKorisnik_Id(id);
    }

    public void deleteByKartaId(Long id){
        rezervacijaRepository.deleteAllByAvionskaKarta_Id(id);
    }


    @Scheduled(fixedDelay = 10000)
    public void updateIsAvailable(){
        List<Rezervacija> rezervacijaList = (List<Rezervacija>) rezervacijaRepository.findAll();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm dd.MM.yyyy");

        System.out.println("Checking rezervacije");
        for(Rezervacija rezervacija : rezervacijaList){
            if(rezervacija != null){
                Date departdate = rezervacija.getAvionskaKarta().getDepartDate();
                Date datenow = new Date();

                if(rezervacija.isAvaliable()) {
                    System.out.println(departdate.toInstant());
                    System.out.println(datenow.toInstant());
                    System.out.println(departdate.toInstant().compareTo(datenow.toInstant()));
                    if (departdate.toInstant().compareTo(datenow.toInstant()) > 0) {
                        System.out.println("days between "+ ChronoUnit.DAYS.between(departdate.toInstant(), datenow.toInstant()));
                        if (ChronoUnit.DAYS.between(departdate.toInstant(), datenow.toInstant()) <= 1) {
                            System.out.println(rezervacija.getId() + "Nije moguce obrisati ovu rezervaciju vise");
                        }else{
                            System.out.println(rezervacija.getId() + "I dalje je moguce obrisati ovu rezervaciju");

                        }
                    }else{
                        rezervacija.setAvaliable(false);
                        rezervacijaRepository.save(rezervacija);
                    }

                }

            }
        }

    }
}
