package br.com.delivery.modules.user.mapper;

import br.com.delivery.modules.user.User;
import br.com.delivery.records.user.UserLoginRecord;
import br.com.delivery.records.user.UserRegisterRecord;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntityLogin(UserLoginRecord userLoginRecord);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntityRegister(UserRegisterRecord userRegisterRecord);
}
