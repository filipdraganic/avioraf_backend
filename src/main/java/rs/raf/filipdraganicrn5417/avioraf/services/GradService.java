package rs.raf.filipdraganicrn5417.avioraf.services;

import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.Grad;
import rs.raf.filipdraganicrn5417.avioraf.repositories.GradRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GradService implements IService<Grad, Long>{

    private final GradRepository gradRepository;

    public GradService(GradRepository gradRepository){
        this.gradRepository = gradRepository;
    }


    @Override
    public Grad save(Grad grad) {
        return gradRepository.save(grad);
    }

    @Override
    public Optional<Grad> findById(Long id) {
        return gradRepository.findById(id);
    }

    @Override
    public List<Grad> findAll() {
        return (List<Grad>) gradRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        gradRepository.deleteById(id);

    }
}
