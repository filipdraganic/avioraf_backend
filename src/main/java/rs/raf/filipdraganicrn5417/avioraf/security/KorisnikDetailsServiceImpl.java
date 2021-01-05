package rs.raf.filipdraganicrn5417.avioraf.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.raf.filipdraganicrn5417.avioraf.model.Korisnik;
import rs.raf.filipdraganicrn5417.avioraf.repositories.KorisnikRepository;

import java.util.List;

import static java.util.Collections.emptyList;

@Service
public class KorisnikDetailsServiceImpl implements UserDetailsService {

    private KorisnikRepository korisnikRepository;

    public KorisnikDetailsServiceImpl(KorisnikRepository korisnikRepository) {
        this.korisnikRepository = korisnikRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<Korisnik> korisnici = (List<Korisnik>) korisnikRepository.findAll();
        Korisnik korisnik = null;

       for(Korisnik forkorisnik : korisnici){
           if(forkorisnik.getUsername().equals(username)){
               korisnik = forkorisnik;
               break;
           }
       }

       if (korisnik == null) {
           throw new UsernameNotFoundException(username);
       }

       return new User(korisnik.getUsername(), korisnik.getPassword(), emptyList());
    }


}
