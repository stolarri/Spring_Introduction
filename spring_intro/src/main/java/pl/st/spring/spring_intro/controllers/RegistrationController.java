package pl.st.spring.spring_intro.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.st.spring.spring_intro.model.domain.User;
import pl.st.spring.spring_intro.model.repositories.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private final UserRepository userRepository;
    private static final Logger log = LoggerFactory.getLogger(RegistrationController.class);
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String prepareRegistrationPage() {
        return "/WEB-INF/views/registration-form.jsp";
    }

    @PostMapping
    public String processRegistrationPage(
            String username,
            String password,
            String firstName,
            String lastName
    ) {
        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setFirst_name(firstName);
        user.setLast_name(lastName);
        user.setActive(true);

        userRepository.save(user);
        log.info("Zapisany user: " + user);

        return "redirect:/index.html";

    }

}
