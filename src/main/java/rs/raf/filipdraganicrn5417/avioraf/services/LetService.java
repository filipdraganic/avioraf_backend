package rs.raf.filipdraganicrn5417.avioraf.services;

import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.Let;
import rs.raf.filipdraganicrn5417.avioraf.repositories.LetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LetService implements IService<Let, Long>{

    private final LetRepository letRepository;

    public LetService(LetRepository letRepository){
        this.letRepository = letRepository;
    }

    @Override
    public Let save(Let let) {
        return letRepository.save(let);
    }

    @Override
    public Optional<Let> findById(Long id) {
        return letRepository.findById(id);
    }

    @Override
    public List<Let> findAll() {
        return (List<Let>) letRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        letRepository.deleteById(id);

    }
}
