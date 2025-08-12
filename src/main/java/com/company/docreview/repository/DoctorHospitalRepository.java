package com.company.docreview.repository;

import com.company.docreview.entity.DoctorHospital;
import com.company.docreview.entity.DoctorHospitalId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorHospitalRepository extends JpaRepository<DoctorHospital, DoctorHospitalId> {
}
