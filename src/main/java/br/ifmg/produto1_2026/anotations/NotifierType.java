package br.ifmg.produto1_2026.anotations;

import br.ifmg.produto1_2026.constants.NotificationTypes;
import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface NotifierType {
    NotificationTypes value();
}
