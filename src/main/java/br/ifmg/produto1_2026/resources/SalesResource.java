package br.ifmg.produto1_2026.resources;

import br.ifmg.produto1_2026.dto.UserDTO;
import br.ifmg.produto1_2026.entities.User;
import br.ifmg.produto1_2026.service.ActivationClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/sales")
public class SalesResource {

    private ActivationClientService activationClientService;

    public SalesResource(ActivationClientService activationClientService) {
        this.activationClientService = activationClientService;
        System.out.println("Camada de resouce");
    }

    @PostMapping
    public ResponseEntity<String> insert(@RequestBody UserDTO dto) {

        User user = new User();
        user.setName("Fulano");
        user.setPhone("123456789");
        user.setEmail("Fulano@gmail.com");
        activationClientService.activate(user, "ativando...");

        return ResponseEntity.ok().body("Venda realizada");
    }

}
