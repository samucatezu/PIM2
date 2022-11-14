package com.samucatezu.pim2.repository;


import com.samucatezu.pim2.models.ERole;
import com.samucatezu.pim2.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
