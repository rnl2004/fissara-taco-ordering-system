package com.fissara.taco.ordering.system.repository;

import com.fissara.taco.ordering.system.model.Role;
import com.fissara.taco.ordering.system.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}
