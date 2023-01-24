package hu.acsaifz.blogapp.service.impl;

import hu.acsaifz.blogapp.exception.ResourceNotFoundException;
import hu.acsaifz.blogapp.model.Role;
import hu.acsaifz.blogapp.repository.RoleRepository;
import hu.acsaifz.blogapp.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;


    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new ResourceNotFoundException("role", "name", roleName));
    }
}
