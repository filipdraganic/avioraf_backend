package rs.raf.filipdraganicrn5417.avioraf.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class AvionskaKompanija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //mora biti jedinstven, ne prikazuje se na frontendu

    @Column(nullable = false, unique = true)
    private String name; //mora biti jedinstveno i mora se sastojati od alfanumerickih karaktera


}
