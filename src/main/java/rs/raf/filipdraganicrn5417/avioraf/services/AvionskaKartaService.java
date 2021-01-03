package rs.raf.filipdraganicrn5417.avioraf.services;

import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.AvionskaKarta;
import rs.raf.filipdraganicrn5417.avioraf.repositories.AvionskaKartaRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AvionskaKartaService implements IService<AvionskaKarta, Long> {

    private final AvionskaKartaRepository avionskaKartaRepository;

    public AvionskaKartaService(AvionskaKartaRepository avionskaKartaRepository) {
        this.avionskaKartaRepository = avionskaKartaRepository;
    }

    @Override
    public AvionskaKarta save(AvionskaKarta avionskaKarta) {
        return avionskaKartaRepository.save(avionskaKarta);
    }

    @Override
    public Optional<AvionskaKarta> findById(Long id) {
        return avionskaKartaRepository.findById(id);
    }

    @Override
    public List<AvionskaKarta> findAll() {
        return (List<AvionskaKarta>) avionskaKartaRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        avionskaKartaRepository.deleteById(id);

    }
}
