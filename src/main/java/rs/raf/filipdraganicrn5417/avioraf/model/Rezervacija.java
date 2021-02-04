package rs.raf.filipdraganicrn5417.avioraf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //mora biti jedinstven, ne prikazuje se na frontendu

    private boolean isAvaliable = true; //da li je rezervacija dostupna ili je istekla (ako je prosao datum polaska)

    @OneToOne
    @JoinColumn(name = "Let_ID", referencedColumnName = "ID")
    private Let let; //rezervisani let

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "Avionska_Karta_ID", referencedColumnName = "ID")
    private AvionskaKarta avionskaKarta; // rezervisana karta

    @JsonIgnore
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "KORISNIK_ID", referencedColumnName = "ID")
    private Korisnik korisnik; // Osoba koja je napravial rezervaciju

    @Override
    public String toString() {
        return "Rezervacija{" +
                "id=" + id +
                ", isAvaliable=" + isAvaliable +
                ", let=" + let +
                ", avionskaKarta=" + avionskaKarta +
                ", korisnik=" + korisnik +
                '}';
    }
}
