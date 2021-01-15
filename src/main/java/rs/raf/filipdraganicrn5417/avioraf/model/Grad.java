package rs.raf.filipdraganicrn5417.avioraf.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.IdentityHashMap;

@Table
@Entity
@Getter
@Setter
public class Grad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //mora biti jedinstven, ne prikazuje se na frontendu

    @Column(nullable = false, unique = true)
    private String name; //ime grada, mora biti jedinstveno


}
