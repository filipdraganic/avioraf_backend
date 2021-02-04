package rs.raf.filipdraganicrn5417.avioraf.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.AvionskaKarta;
import rs.raf.filipdraganicrn5417.avioraf.repositories.AvionskaKartaPaginationRepository;
import rs.raf.filipdraganicrn5417.avioraf.repositories.AvionskaKartaRepository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
public class AvionskaKartaService implements IService<AvionskaKarta, Long> {

    private final AvionskaKartaRepository avionskaKartaRepository;

    private final AvionskaKartaPaginationRepository avionskaKartaPaginationRepository;

    private final RezervacijaService rezervacijaService;

    public AvionskaKartaService(AvionskaKartaRepository avionskaKartaRepository, AvionskaKartaPaginationRepository avionskaKartaPaginationRepository, RezervacijaService rezervacijaService) {
        this.avionskaKartaRepository = avionskaKartaRepository;
        this.avionskaKartaPaginationRepository = avionskaKartaPaginationRepository;
        this.rezervacijaService = rezervacijaService;
    }

    @Override
    public AvionskaKarta save(AvionskaKarta avionskaKarta) {
        return avionskaKartaRepository.save(avionskaKarta);
    }

    @Override
    public Optional<AvionskaKarta> findById(Long id) {
        return avionskaKartaRepository.findById(id);
    }


    public Page<AvionskaKarta> findAll(int pageNumber, int size) {
        Pageable page = PageRequest.of(pageNumber, size);
        return avionskaKartaPaginationRepository.findAll(page);
    }

    @Override
    public void deleteById(Long id) {
        avionskaKartaRepository.deleteById(id);

    }

    @Override
    public List<AvionskaKarta> findAll(){
        return (List<AvionskaKarta>) avionskaKartaRepository.findAll();
    }

    public List<AvionskaKarta> findAllByAvionskaKompanija(Long id) {
        return avionskaKartaRepository.findAlLByAvionskaKompanija_Id(id);
    }

    public void deleteByAvionskaKompanijaId(Long id){
        avionskaKartaRepository.deleteAvionskaKartasByAvionskaKompanija_Id(id);

    }

    public void reduceCount(AvionskaKarta karta, int amount){
        karta.setCount(karta.getCount() - amount);

        if(karta.getCount() == 0){
            rezervacijaService.deleteByKartaId(karta.getId());
            avionskaKartaRepository.deleteById(karta.getId());
        }else{
            avionskaKartaRepository.save(karta);
        }
    }


}
