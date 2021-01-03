package rs.raf.filipdraganicrn5417.avioraf.model;

import lombok.Data;

import javax.persistence.*;
import java.util.IdentityHashMap;

@Data
@Entity
public class Grad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //mora biti jedinstven, ne prikazuje se na frontendu

    @Column(nullable = false, unique = true)
    private String name; //ime grada, mora biti jedinstveno


}
