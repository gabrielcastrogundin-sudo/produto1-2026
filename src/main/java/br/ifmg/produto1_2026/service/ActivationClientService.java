package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.entities.User;
import br.ifmg.produto1_2026.util.Notificator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Service;

@Service
public class ActivationClientService {
    private Notificator notificator;

    public ActivationClientService(Notificator notificator) {
        System.out.println("Iniciando Ativação cliente");
    }

    public void activate(User user, String message) {
        notificator.notify_user(user, message);
    }

    @PostConstruct
    public void init() {
        System.out.println("Metodo ativado apos o constructor");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("Metodo ativado para destruir a ativação");
    }

}
