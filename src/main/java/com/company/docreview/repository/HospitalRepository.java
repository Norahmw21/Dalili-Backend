package com.company.docreview.repository;

import com.company.docreview.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface HospitalRepository extends JpaRepository<Hospital,Long> {
}
