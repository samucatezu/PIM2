package com.samucatezu.pim2.repository;

import com.samucatezu.pim2.models.Insurance;
import com.samucatezu.pim2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {

    List<Insurance> findByInsuranceId(Long insuranceId);
}
