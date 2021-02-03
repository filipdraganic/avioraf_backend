package rs.raf.filipdraganicrn5417.avioraf.repositories;

import org.springframework.data.repository.CrudRepository;
import rs.raf.filipdraganicrn5417.avioraf.model.AvionskaKarta;

import java.util.List;

public interface AvionskaKartaRepository extends CrudRepository<AvionskaKarta, Long> {

    List<AvionskaKarta> findAlLByAvionskaKompanija_Id(Long id);

    void deleteAvionskaKartasByAvionskaKompanija_Id(Long id);

}
