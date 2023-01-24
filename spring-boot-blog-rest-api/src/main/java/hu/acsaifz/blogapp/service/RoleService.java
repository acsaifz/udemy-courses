package hu.acsaifz.blogapp.service;

import hu.acsaifz.blogapp.model.Role;

public interface RoleService {
    Role findRoleByName(String roleName);

}
