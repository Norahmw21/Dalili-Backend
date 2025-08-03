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
public class FavoriteId implements Serializable {
    private static final long serialVersionUID = -5624879315226386201L;
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "doctor_id", nullable = false)
    private Long doctorId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FavoriteId entity = (FavoriteId) o;
        return Objects.equals(this.doctorId, entity.doctorId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doctorId, userId);
    }

}