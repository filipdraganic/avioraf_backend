package rs.raf.filipdraganicrn5417.avioraf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
@Getter
@Setter
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //mora biti jedinstven, ne prikazuje se na frontendu

    @Column(unique = true, nullable = false)
    private String username; //mora biti jedinstven

    @Column(nullable = false)
    private String password; //mora imati bar 6 karaktera i sadrzati slova i brojeve, u bazi se cuva enkriptovan.

    @Column(nullable = false)
    private UserType userType; //User ili Admin

    @OneToMany(cascade = CascadeType.ALL)
    private List<Rezervacija> bookings; //lista rezervacija (samo kod obicnog korisnika)


}
