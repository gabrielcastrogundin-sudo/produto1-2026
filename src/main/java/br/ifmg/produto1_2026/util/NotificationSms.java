package br.ifmg.produto1_2026.util;

import br.ifmg.produto1_2026.entities.User;

//@Component
public class NotificationSms implements Notificator {

    private boolean upperCase;

    public NotificationSms() {
        System.out.println("Sms enviado com sucesso!");
    }
    public void notify_user(User user, String message) {
        if(upperCase) {
            message = message.toUpperCase();
        }
        System.out.printf("Notificando %s através do telefone %s: %s\n",
                user.getName(), user.getEmail(), message);
    }

    public boolean isUpperCase() {
        return upperCase;
    }

    public void setUpperCase(boolean upperCase) {
        this.upperCase = upperCase;
    }

}