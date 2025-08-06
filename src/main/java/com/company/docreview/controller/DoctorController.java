package com.company.docreview.controller;


import com.company.docreview.dto.DoctorDTO;
import com.company.docreview.dto.DoctorWithHospitalDTO;
import com.company.docreview.entity.Doctor;
import com.company.docreview.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:5183")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;
    @GetMapping("/all")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<Doctor> doctors = doctorService.getAllDoctors();

        List<DoctorDTO> doctorDTOs = doctors.stream()
                .map(doc -> new DoctorDTO(
                        doc.getId(),
                        doc.getName(),
                        doc.getBio(),
                        doc.getPhotoUrl(),
                        doc.getYearsOfExperience(),
                        doc.getExperience(),
                        doc.getContactPhone(),
                        doc.getContactEmail(),
                        doc.getSpecialty()
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(doctorDTOs);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable Long id) {
        Optional<Doctor> doctorOptional = doctorService.getDoctorById(id);

        if (doctorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Doctor doc = doctorOptional.get();

        DoctorDTO dto = new DoctorDTO(
                doc.getId(),
                doc.getName(),
                doc.getBio(),
                doc.getPhotoUrl(),
                doc.getYearsOfExperience(),
                doc.getExperience(),
                doc.getContactPhone(),
                doc.getContactEmail(),
                doc.getSpecialty()
        );

        return ResponseEntity.ok(dto);
    }


    @PostMapping
    public ResponseEntity<Doctor> createDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.createDoctor(doctor), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        doctor.setId(id);
        return new ResponseEntity<>(doctorService.updateDoctor(doctor), HttpStatus.OK);
    }
    @GetMapping("/with-hospitals")//for admin mangment
    public ResponseEntity<List<DoctorWithHospitalDTO>> getDoctorsWithHospitals() {
        List<DoctorWithHospitalDTO> doctorsWithHospitals = doctorService.getDoctorsWithHospitals();
        if (doctorsWithHospitals.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(doctorsWithHospitals, HttpStatus.OK);
    }

    @GetMapping("/specialties")
    public ResponseEntity<List<String>> getAllSpecialties() {
        return new ResponseEntity<>(doctorService.getAllSpecialties(), HttpStatus.OK);
    }

    @GetMapping("/search-with-hospital")
    public ResponseEntity<List<DoctorWithHospitalDTO>> searchDoctorsWithHospital(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) Long hospitalId) {

        List<DoctorWithHospitalDTO> result = doctorService.searchDoctorsWithHospital(name, specialty, hospitalId);
        if (result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long id) {
        if (doctorService.deleteDoctor(id)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Doctor>> searchDoctors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String specialty,
            @RequestParam(required = false) Double minRating) {

        List<Doctor> doctors = doctorService.searchDoctors(name, specialty, minRating);
        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

}
