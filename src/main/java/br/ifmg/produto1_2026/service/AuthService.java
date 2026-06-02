package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.dto.*;
import br.ifmg.produto1_2026.entities.PasswordRecover;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.User;
import br.ifmg.produto1_2026.repositories.PasswordRecoverRepository;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UserRepository;
import br.ifmg.produto1_2026.service.exception.ResourceNotFound;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Value("${spring.mail.username}")
    private String defaultSender;
    @Value("${email.password-recover.uri}")
    private String recoverUri;
    @Value("${email.password-recover.token.minutes}")
    private Long tokenMinutes;
    @Autowired
    private PasswordRecoverRepository passwordRecoverRepository;
    @Autowired
    private EmailService emailService;

    @Transactional
    public void createRecoverToken(RequestTokenDTO body) {
        User user = userRepository.findByEmail(body.getEmail());
        if (user == null) {
            throw new ResourceNotFound("Email not found");
        }
        String token = UUID.randomUUID().toString();
        PasswordRecover entity = new PasswordRecover();
        entity.setToken(token);
        entity.setExpiration(Instant.now().plusSeconds(tokenMinutes * 60L));
        entity.setEmail(body.getEmail());
        passwordRecoverRepository.save(entity);
        String text = "Acesse o link para definir uma nova senha (válido por " + tokenMinutes + " minutos):\n\n"
                + recoverUri + token;
        emailService.sendMail( new EmailDTO(entity.getEmail(), "Recperação de senha", text));
    }

    private void copyDtoToUser(UserDTO dto, User user) {
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());

        user.setPhone(dto.getPhone());
        user.getPerfils().clear();
        for(PerfilDTO perfilDTO : dto.getPerfils()){
            Perfil perfil = perfilRepository.getReferenceById(perfilDTO.getId());
            user.getPerfils().add(perfil);
        }
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {

        User user = new User();
        copyDtoToUser(dto, user);
        user.setPassword(encoder.encode(dto.getPassword()));

        userRepository.save(user);

        return new UserDTO(user);
    }

    public void saveNewPassword(@Valid NewPasswordDTO dto) {

        List<PasswordRecover> list = passwordRecoverRepository.searchValidTokens(dto.getToken(), Instant.now());

        if(list.isEmpty()) {
            throw new ResourceNotFound("Token not found or expired");
        }

        User user = userRepository.findByEmail(list.getFirst().getEmail());

        user.setPassword(encoder.encode(dto.getPassword()));

    }
}
