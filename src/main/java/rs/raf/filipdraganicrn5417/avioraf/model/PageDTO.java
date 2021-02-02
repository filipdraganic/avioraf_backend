package rs.raf.filipdraganicrn5417.avioraf.model;

import lombok.Data;

import java.util.List;

@Data
public class PageDTO {

    private int size;
    private List<AvionskaKarta> avionskaKartaList;

}
