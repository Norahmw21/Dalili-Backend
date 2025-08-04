package com.company.docreview.controller;

import com.company.docreview.entity.Hospital;
import com.company.docreview.service.HospitalService;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hospitals")
public class HospitalController {
    private HospitalService hospitalService;
    public HospitalController(HospitalService hospitalService) {
        this.hospitalService = hospitalService;
    }
    @GetMapping()
    public List<Hospital> getAllHospitals() {
        return hospitalService.getAllHospitals();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Hospital> getHospitalById(@PathVariable Long id) {
        return hospitalService.getHospitalById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public Hospital createHospital(@RequestBody Hospital hospital) {
        return hospitalService.createHospital(hospital);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Hospital> updateHospital(@PathVariable Long id, @RequestBody Hospital hospital) {
        try{
            Hospital updatedHospital = hospitalService.updateHospital(id, hospital);
            return ResponseEntity.ok(updatedHospital);
        }catch(Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
