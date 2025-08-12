package com.company.docreview.service;


import com.company.docreview.entity.Hospital;
import com.company.docreview.repository.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Optional<Hospital> getHospitalById(Long id) {
        return hospitalRepository.findById(id);
    }

    public Hospital createHospital(Hospital hospital) {
        return hospitalRepository.save(hospital);
    }

    public Hospital updateHospital(Long id, Hospital updatedHospital) {
        return hospitalRepository.findById(id).map(hospital -> {
            hospital.setName(updatedHospital.getName());
            hospital.setAddress(updatedHospital.getAddress());
            hospital.setLatitude(updatedHospital.getLatitude());
            hospital.setLongitude(updatedHospital.getLongitude());
            hospital.setWebsiteUrl(updatedHospital.getWebsiteUrl());
            return hospitalRepository.save(hospital);
        }).orElseThrow(() -> new RuntimeException("Hospital not found with id " + id));
    }

    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
}
