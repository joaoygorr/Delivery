package br.com.delivery.modules.user;

import br.com.delivery.modules.user.records.ResponseRecord;

public interface UserService {
    ResponseRecord login(User user);

    ResponseRecord register(User user);
}
