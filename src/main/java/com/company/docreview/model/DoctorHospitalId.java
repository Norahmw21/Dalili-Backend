package com.company.docreview.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class DoctorHospitalId implements Serializable {
    private static final long serialVersionUID = 2054664126550165175L;
    @Column(name = "doctor_id", nullable = false)
    private Integer doctorId;

    @Column(name = "hospital_id", nullable = false)
    private Integer hospitalId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        DoctorHospitalId entity = (DoctorHospitalId) o;
        return Objects.equals(this.doctorId, entity.doctorId) &&
                Objects.equals(this.hospitalId, entity.hospitalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, hospitalId);
    }

}