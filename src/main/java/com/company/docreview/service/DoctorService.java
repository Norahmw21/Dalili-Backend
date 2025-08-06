package com.company.docreview.service;

import com.company.docreview.dto.DoctorWithHospitalDTO;
import com.company.docreview.dto.TopDoctorDto;
import com.company.docreview.entity.Doctor;
import com.company.docreview.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public List<String> getAllSpecialties() {
        return doctorRepository.findDistinctSpecialties();
    }

    public Optional<Doctor> getDoctorById(Long id) {
        return doctorRepository.findById(id);
    }

    public Doctor createDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor updateDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public boolean deleteDoctor(Long id) {
        if (doctorRepository.existsById(id)) {
            doctorRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<DoctorWithHospitalDTO> getDoctorsWithHospitals() {
        return doctorRepository.findDoctorsWithHospitals();
    }

    public List<DoctorWithHospitalDTO> searchDoctorsWithHospital(String name, String specialty, Long hospitalId) {
        String searchName = (name != null && !name.trim().isEmpty()) ? name.trim() : null;
        String searchSpecialty = (specialty != null && !specialty.trim().isEmpty()) ? specialty.trim() : null;

        return doctorRepository.findDoctorsWithHospitals(searchName, searchSpecialty, hospitalId);
    }

    public List<TopDoctorDto> getTopDoctors() {
        Pageable topFive = PageRequest.of(0, 5);
        return doctorRepository.findTopDoctors(topFive);
    }


    /**
     * Searches for doctors based on multiple criteria.
     *
     * @param name      The name of the doctor (partial match, case-insensitive).
     * @param specialty The specialty of the doctor (partial match, case-insensitive).
     * @param minRating The minimum average rating.
     * @return A list of doctors matching the criteria.
     */
    public List<Doctor> searchDoctors(String name, String specialty, Double minRating) {
        // Simple and flexible way to handle various combinations of search criteria
        if (name != null && specialty != null && minRating != null) {
            return doctorRepository.findByNameAndSpecialtyAndAverageRating(name, specialty, minRating);
        } else if (name != null && specialty != null) {
            return doctorRepository.findByNameContainingIgnoreCaseAndSpecialtyContainingIgnoreCase(name, specialty);
        } else if (minRating != null) {
            return doctorRepository.findByAverageRatingGreaterThanEqual(minRating);
        } else if (name != null) {
            return doctorRepository.findByNameContainingIgnoreCaseAndSpecialtyContainingIgnoreCase(name, "");
        } else if (specialty != null) {
            return doctorRepository.findByNameContainingIgnoreCaseAndSpecialtyContainingIgnoreCase("", specialty);
        } else {
            return doctorRepository.findAll();
        }
    }
}
