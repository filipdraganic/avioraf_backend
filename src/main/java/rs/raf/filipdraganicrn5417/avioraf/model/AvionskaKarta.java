package rs.raf.filipdraganicrn5417.avioraf.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AvionskaKarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //mora biti jedinstven, ne prikazuje se na frontendu

    @ManyToOne
    @JoinColumn(name = "AvionskaKompanija_ID", referencedColumnName = "ID")
    private AvionskaKompanija avionskaKompanija; //kompanija za koju je vezana karta

    private boolean oneway; //da li je karta u jednom pravcu ili povratna

    private Date departDate; //datum polaska

    private Date returnDate; //datum povratka (samo kod povratnih karata)

    @OneToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "Let_ID", referencedColumnName = "ID")
    private Let let; //let za koji je karta vezana

    private Long Count; //broj (>=0) dostupnih karata



}
