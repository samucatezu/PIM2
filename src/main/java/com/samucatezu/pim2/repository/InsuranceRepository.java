package com.samucatezu.pim2.repository;

import com.samucatezu.pim2.models.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    List<Insurance> findByUserId(Long postId);

    @Transactional
    void deleteByUserId(long tutorialId);
}
