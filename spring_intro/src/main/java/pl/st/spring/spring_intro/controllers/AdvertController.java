package pl.st.spring.spring_intro.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import pl.st.spring.spring_intro.model.domain.Advert;
import pl.st.spring.spring_intro.model.domain.User;
import pl.st.spring.spring_intro.model.repositories.AdvertRepository;
import pl.st.spring.spring_intro.model.repositories.UserRepository;

import java.security.Principal;
import java.time.LocalDateTime;

@Controller
public class AdvertController {

    private static final Logger log = LoggerFactory.getLogger(AdvertController.class);

    private final UserRepository userRepository;
    private final AdvertRepository advertRepository;


    public AdvertController(UserRepository userRepository, AdvertRepository advertRepository) {
        this.userRepository = userRepository;
        this.advertRepository = advertRepository;
    }

    @PostMapping("/add-advert")
    public String addAdvert(String title, String description, Principal principal) {
        String username = principal.getName();
        User user = userRepository.findByUsername(username);
        Advert newAdvert = new Advert();
        newAdvert.setTitle(title);
        newAdvert.setDescription(description);
        newAdvert.setOwner(user);
        newAdvert.setPosted(LocalDateTime.now().withNano(0).withSecond(0));

        log.info("Zapis zgłoszenia...");
        newAdvert = advertRepository.save(newAdvert);
        log.info("Ogłoszenie zapisane: " + newAdvert);

        return "redirect:/";
    }

}


