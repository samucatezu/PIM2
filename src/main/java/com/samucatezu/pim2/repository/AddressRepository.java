package com.samucatezu.pim2.repository;

import com.samucatezu.pim2.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
