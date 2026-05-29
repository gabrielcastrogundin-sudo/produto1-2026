package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.PerfilDTO;
import br.ifmg.produto1_2026.dto.RequestTokenDTO;
import br.ifmg.produto1_2026.dto.UserDTO;
import br.ifmg.produto1_2026.dto.UserInsertDTO;
import br.ifmg.produto1_2026.entities.Perfil;
import br.ifmg.produto1_2026.entities.User;
import br.ifmg.produto1_2026.repositories.PerfilRepository;
import br.ifmg.produto1_2026.repositories.UserRepository;
import br.ifmg.produto1_2026.service.AuthService;
import br.ifmg.produto1_2026.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthResource {

    @Autowired
    private UserService userService;

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDTO> signup(@Valid @RequestBody UserInsertDTO dto) {
        UserDTO returnDTO =  userService.insert(dto);
        Perfil perfil = perfilRepository.findByName(returnDTO.getName());
        //        dto.setEmail();
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(returnDTO.getId())
                .toUri();

        return ResponseEntity.created(location).body(returnDTO);
    }

    @PostMapping(value = "/recover-token")
    public ResponseEntity<Void> createRecoverToken(@Valid @RequestBody RequestTokenDTO dto) {
        AuthService.createRecoverToken(dto);
        return ResponseEntity.noContent().build();
    }
}
