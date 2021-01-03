package rs.raf.filipdraganicrn5417.avioraf.services;

import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.Rezervacija;
import rs.raf.filipdraganicrn5417.avioraf.repositories.RezervacijaRepository;

import java.util.List;
import java.util.Optional;

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
}
