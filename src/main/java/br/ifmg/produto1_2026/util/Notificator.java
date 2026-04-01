package br.ifmg.produto1_2026.util;

import br.ifmg.produto1_2026.entities.User;

public interface Notificator {

    public void notify_user(User user, String message);

}
