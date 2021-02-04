package rs.raf.filipdraganicrn5417.avioraf.repositories;

import org.springframework.data.repository.CrudRepository;
import rs.raf.filipdraganicrn5417.avioraf.model.Rezervacija;

import java.util.List;

public interface RezervacijaRepository extends CrudRepository<Rezervacija, Long> {

    List<Rezervacija> getRezervacijaByKorisnik_Id(Long id);

    void deleteAllByAvionskaKarta_Id(Long id);

}
