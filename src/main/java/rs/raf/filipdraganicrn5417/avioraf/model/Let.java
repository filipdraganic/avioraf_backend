package rs.raf.filipdraganicrn5417.avioraf.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Let {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //mora biti jedinstven, ne prikazuje se na frontendu

    @OneToMany
    private List<AvionskaKarta> avionskeKarte; //lista karta za ovaj let

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Origin_Grad_ID", referencedColumnName = "ID")
    private Grad origin; //grad iz kog se polece

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Destination_Grad_ID", referencedColumnName = "ID")
    private Grad destination; //grad u koji se putuje



}
