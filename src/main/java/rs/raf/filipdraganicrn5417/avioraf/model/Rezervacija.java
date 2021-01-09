package rs.raf.filipdraganicrn5417.avioraf.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //mora biti jedinstven, ne prikazuje se na frontendu

    private boolean isAvaliable; //da li je rezervacija dostupna ili je istekla (ako je prosao datum polaska)

    @OneToOne
    @JoinColumn(name = "Let_ID", referencedColumnName = "ID")
    private Let let; //rezervisani let

    @OneToOne
    @JoinColumn(name = "Avionska_Karta_ID", referencedColumnName = "ID")
    private AvionskaKarta avionskaKarta; // rezervisana karta
}
