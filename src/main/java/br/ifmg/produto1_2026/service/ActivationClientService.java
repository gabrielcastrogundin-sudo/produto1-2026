package br.ifmg.produto1_2026.service;

import br.ifmg.produto1_2026.entities.User;
import br.ifmg.produto1_2026.util.Notificator;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivationClientService {

    //    @Autowired
//    private Notificator notificator;
    private List<Notificator> notificators;
    @Autowired
    public ActivationClientService(List<Notificator> notificators) {

        System.out.println("Iniciando Ativação cliente");

        this.notificators = notificators;

    }

//    outra forma de utilizar o autowired para instanciar e injetar classes

    public ActivationClientService() {

        System.out.println("Iniciando Ativação cliente com construtor sem parametros");
    }

    public void activate(User user, String message) {

//        notificators.notify_user(user, message);
        for (Notificator notificator : notificators) {
            notificator.notify_user(user, message);
        }
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
