package rs.raf.filipdraganicrn5417.avioraf.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class AvionskaKarta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //mora biti jedinstven, ne prikazuje se na frontendu

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "AvionskaKompanija_ID", referencedColumnName = "id")
    private AvionskaKompanija avionskaKompanija; //kompanija za koju je vezana karta

    @Column
    private boolean oneway; //da li je karta u jednom pravcu ili povratna

    @Column
    private Date departDate; //datum polaska

    @Column
    private Date returnDate; //datum povratka (samo kod povratnih karata)

    @ManyToOne
    @JoinColumn(name = "Let_ID", referencedColumnName = "id")
    private Let let; //let za koji je karta vezana

    private long Count; //broj (>=0) dostupnih karata



}
