package br.ifmg.produto1_2026.config;

import br.ifmg.produto1_2026.anotations.NotifierType;
import br.ifmg.produto1_2026.constants.NotificationTypes;
import br.ifmg.produto1_2026.service.ActivationClientService;
import br.ifmg.produto1_2026.util.NotificationEmail;
import br.ifmg.produto1_2026.util.NotificationSms;
import br.ifmg.produto1_2026.util.Notificator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

@Configuration
public class ProductConfig {

    @Profile("prod")
    @NotifierType(value = NotificationTypes.EMAIL)
    @Bean
    public NotificationEmail notificationEmail() {

        NotificationEmail notificationEmail = new NotificationEmail("smtp.google.com");
        notificationEmail.setUpperCase(true);

        return notificationEmail;
    }

/*
    @Bean
    public ActivationClientService activationClientService
            (Notificator notificator) {

        ActivationClientService activationClientService =
                new ActivationClientService(notificator);

        return activationClientService;
    }
*/

//    @Primary // remove a ambiguidade entre os beans

    @Profile("dev")
    @NotifierType(value = NotificationTypes.SMS)
    @Bean
    public Notificator notificatorSms() {

        NotificationSms notificatorSms = new NotificationSms();
        notificatorSms.setUpperCase(true);

        return notificatorSms;
    }

}
