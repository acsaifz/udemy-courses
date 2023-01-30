package hu.acsaifz.blogapp.service.mapper;

import hu.acsaifz.blogapp.model.User;
import hu.acsaifz.blogapp.model.dto.auth.RegistrationRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toUser(RegistrationRequest dto);
}
