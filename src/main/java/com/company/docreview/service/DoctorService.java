package com.company.docreview.service;

import com.company.docreview.entity.Doctor;
import com.company.docreview.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
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
