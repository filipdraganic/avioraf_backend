package rs.raf.filipdraganicrn5417.avioraf.factory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import rs.raf.filipdraganicrn5417.avioraf.model.*;
import rs.raf.filipdraganicrn5417.avioraf.repositories.*;


import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Callable;

@Component
public class Factory implements CommandLineRunner {

    private final AvionskaKartaRepository avionskaKartaRepository;
    private final AvionskaKompanijaRepository avionskaKompanijaRepository;
    private final KorisnikRepository korisnikRepository;
    private final LetRepository letRepository;
    private final RezervacijaRepository rezervacijaRepository;
    private final GradRepository gradRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public Factory(AvionskaKartaRepository avionskaKartaRepository, AvionskaKompanijaRepository avionskaKompanijaRepository, KorisnikRepository korisnikRepository, LetRepository letRepository, RezervacijaRepository rezervacijaRepository, GradRepository gradRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.avionskaKartaRepository = avionskaKartaRepository;
        this.avionskaKompanijaRepository = avionskaKompanijaRepository;
        this.korisnikRepository = korisnikRepository;
        this.letRepository = letRepository;
        this.rezervacijaRepository = rezervacijaRepository;
        this.gradRepository = gradRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Beep boop... Data loading in progress...");

        String secret = "123";
        SimpleDateFormat dateformat = new SimpleDateFormat("dd-M-yyyy");

        String[] usernames = {"petar", "metar", "vetar", "patka"};
        String[] sifre = {"petar123", "metar123", "vetar123", "patka123"};
        String[] kompanije = {"AvioDenon", "AvioYamaha", "AvioMarantz"};
        String[] gradovi = {"Rejkavik", "Koupavorig", "Habnarfjerdir", "Akirejri", "Rejkjanesbajr"};
        String[] odlazniDatumi = {"22-10-2010", "21-10-2010", "20-10-2010", "23-10-2010", "24-10-2010", "25-10-2010", "26-10-2010", "27-10-2010", "28-10-2022", "29-10-2022"};
        String[] povratniDatumi = {"01-11-2010", "02-11-2010", "03-11-2010", "04-11-2010", "05-11-2010", "06-11-2010", "07-11-2010", "08-11-2010", "09-11-2022", "10-11-2022"};

        Iterator<String> usernamesIterator = Arrays.stream(usernames).iterator();
        Iterator<String> passwordIterator = Arrays.stream(sifre).iterator();
        Iterator<String> kompanijeIterator = Arrays.stream(kompanije).iterator();
        Iterator<String> gradoviIterator = Arrays.stream(gradovi).iterator();
        Iterator<String> odlaznidatumiIterator = Arrays.stream(odlazniDatumi).iterator();
        Iterator<String> povratnidatumiIterator = Arrays.stream(povratniDatumi).iterator();

        ///////////////////////////
        //INICIJALIZACIJA KORISNIKA
        ///////////////////////////
        while(usernamesIterator.hasNext() && passwordIterator.hasNext()){

            Korisnik korisnik = new Korisnik();

            korisnik.setPassword(bCryptPasswordEncoder.encode(passwordIterator.next()));
            korisnik.setUsername(usernamesIterator.next());
            korisnik.setUserType(UserType.USER);
            if (korisnik.getUsername().equals("patka")){
                korisnik.setUserType(UserType.ADMIN);
            }
            korisnikRepository.save(korisnik);
        }
        System.out.println("Krrrr... Users loaded");

        /////////////////////////
        //INICIJALIZACIJA GRADOVA
        /////////////////////////
        List<Grad> listaGradova = new ArrayList<>();
        long i = 0;
        while(gradoviIterator.hasNext()){

            Grad grad = new Grad();

            grad.setName(gradoviIterator.next());

            gradRepository.save(grad);

            Optional<Grad> getovangrad = gradRepository.findById(i);
            if(getovangrad.isPresent()){
                listaGradova.add(getovangrad.get());
            }
            i++;
        }
        System.out.println("*Boom* ... cities loaded");

        ///////////////////////////////
        //INICIJALIZACIJA AVIOKOMPANIJA
        ///////////////////////////////
        while(kompanijeIterator.hasNext()){
            AvionskaKompanija kompanija = new AvionskaKompanija();

            kompanija.setName(kompanijeIterator.next());

            avionskaKompanijaRepository.save(kompanija);
        }

        Iterator<Grad> getovaniGradoviIterator = listaGradova.listIterator();
        ///////////////////////
        //INICIJALIZACIJA LETOVA
        ///////////////////////
        int brojLetova = 10;
        for(int j = 0; j < brojLetova; j++){
            Let let = new Let();

            if(getovaniGradoviIterator.hasNext()){

                let.setDestination(getovaniGradoviIterator.next());

            }else{
                getovaniGradoviIterator = listaGradova.listIterator();
                let.setDestination(getovaniGradoviIterator.next());
            }

            if(getovaniGradoviIterator.hasNext()){
                let.setOrigin(getovaniGradoviIterator.next());
            }else{
                getovaniGradoviIterator = listaGradova.listIterator();
                let.setOrigin(getovaniGradoviIterator.next());
            }

            letRepository.save(let);
        }
        System.out.println("Ummm... Flights loaded... I guess?");
        ////////////////////////
        //INICIJALIZACIJA KARATA
        ////////////////////////
        while(odlaznidatumiIterator.hasNext() && povratnidatumiIterator.hasNext()){
            Random random = new Random();
            long randomLet = random.nextInt(brojLetova);
            long randomKompanija = random.nextInt(kompanije.length);
            boolean randomBoolean = random.nextBoolean();
            long randomCount = random.nextInt(100);

            AvionskaKarta avionskaKarta = new AvionskaKarta();

            Optional<AvionskaKompanija> izabranaKompanija = avionskaKompanijaRepository.findById(randomKompanija);
            izabranaKompanija.ifPresent(avionskaKarta::setAvionskaKompanija);
            while(!izabranaKompanija.isPresent()){
                randomKompanija = random.nextInt(kompanije.length);
                izabranaKompanija = avionskaKompanijaRepository.findById(randomKompanija);
                izabranaKompanija.ifPresent(avionskaKarta::setAvionskaKompanija);

            }
//            System.out.println(izabranaKompanija);
            Optional<Let> izabranLet = letRepository.findById(randomLet);
            izabranLet.ifPresent(avionskaKarta::setLet);



            avionskaKarta.setOneway(randomBoolean);
            avionskaKarta.setCount(randomCount+1);

            avionskaKarta.setDepartDate(dateformat.parse(odlaznidatumiIterator.next()));

            if(!randomBoolean){
                avionskaKarta.setReturnDate(dateformat.parse(povratnidatumiIterator.next()));
            }else{
                povratnidatumiIterator.next();
            }

            avionskaKartaRepository.save(avionskaKarta);
        }

        System.out.println("Tickets loaded... or maybe...");


    }
}
