package rs.raf.filipdraganicrn5417.avioraf.services;

import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.AvionskaKompanija;
import rs.raf.filipdraganicrn5417.avioraf.repositories.AvionskaKartaRepository;
import rs.raf.filipdraganicrn5417.avioraf.repositories.AvionskaKompanijaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AvionskaKompanijaService implements IService<AvionskaKompanija, Long>{

    private final AvionskaKompanijaRepository avionskaKompanijaRepository;

    public AvionskaKompanijaService(AvionskaKompanijaRepository avionskaKompanijaRepository){
        this.avionskaKompanijaRepository = avionskaKompanijaRepository;
    }


    @Override
    public AvionskaKompanija save(AvionskaKompanija avionskaKompanija) {
        return avionskaKompanijaRepository.save(avionskaKompanija);
    }

    @Override
    public Optional<AvionskaKompanija> findById(Long id) {
        return avionskaKompanijaRepository.findById(id);
    }

    @Override
    public List<AvionskaKompanija> findAll() {
        return (List<AvionskaKompanija>) avionskaKompanijaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        avionskaKompanijaRepository.deleteById(id);

    }
}
