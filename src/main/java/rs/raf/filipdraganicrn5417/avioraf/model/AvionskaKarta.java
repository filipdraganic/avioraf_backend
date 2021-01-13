package rs.raf.filipdraganicrn5417.avioraf.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class AvionskaKarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //mora biti jedinstven, ne prikazuje se na frontendu

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "AvionskaKompanija_ID", referencedColumnName = "ID")
    private AvionskaKompanija avionskaKompanija; //kompanija za koju je vezana karta

    private boolean oneway; //da li je karta u jednom pravcu ili povratna

    private Date departDate; //datum polaska

    private Date returnDate; //datum povratka (samo kod povratnih karata)

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "Let_ID", referencedColumnName = "ID")
    private Let let; //let za koji je karta vezana

    private long Count; //broj (>=0) dostupnih karata



}
