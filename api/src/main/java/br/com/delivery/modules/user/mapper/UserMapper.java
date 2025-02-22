package br.com.delivery.modules.user.mapper;

import br.com.delivery.mapper.DtoMapper;
import br.com.delivery.modules.user.User;
import br.com.delivery.records.user.UserRecord;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.ERROR)
public abstract class UserMapper implements DtoMapper<User, UserRecord> {

    public abstract User toEntityAuth(UserRecord dto);
}
