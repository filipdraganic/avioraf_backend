package rs.raf.filipdraganicrn5417.avioraf.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Let {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //mora biti jedinstven, ne prikazuje se na frontendu

    @JsonIgnore
    @OneToMany(mappedBy = "let")
    private List<AvionskaKarta> avionskeKarte; //lista karta za ovaj let

    @ManyToOne()
    @JoinColumn(name = "Origin_Grad_ID", referencedColumnName = "ID")
    private Grad origin; //grad iz kog se polece

    @ManyToOne()
    @JoinColumn(name = "Destination_Grad_ID", referencedColumnName = "ID")
    private Grad destination; //grad u koji se putuje



}
