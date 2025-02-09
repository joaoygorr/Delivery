package br.com.delivery.services.user;

import br.com.delivery.modules.user.User;
import br.com.delivery.records.user.ResponseRecord;

public interface UserService {
    ResponseRecord login(User user);

    ResponseRecord register(User user);
}
