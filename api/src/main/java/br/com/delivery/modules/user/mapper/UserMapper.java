package br.com.delivery.modules.user.mapper;

import br.com.delivery.modules.user.User;
import br.com.delivery.modules.user.records.LoginRecord;
import br.com.delivery.modules.user.records.UserRecord;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
public abstract class UserMapper {

    public abstract User toEntityRegister(UserRecord dto);

    public abstract User toEntityLogin(LoginRecord loginRecord);
}
