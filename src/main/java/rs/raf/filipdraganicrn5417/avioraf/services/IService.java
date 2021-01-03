package rs.raf.filipdraganicrn5417.avioraf.services;


import java.util.List;
import java.util.Optional;

public interface IService<T, ID> {
    T save(T var1);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

}
