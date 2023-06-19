package com.wktechnology.bloodbank.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "BLOOD_COMPATIBILITY")
public class BloodCompatibilityEntity {

    @EmbeddedId
    private BloodCompatibilityId id;

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    @MapsId("giverId")
    private BloodTypeEntity giver;

    @ToString.Exclude
    @ManyToOne(fetch = LAZY)
    @MapsId("receiverId")
    private BloodTypeEntity receiver;

    @Column(nullable = false, name = "created_at")
    private OffsetDateTime createdAt;

    @Column(nullable = false, name = "updated_at")
    private OffsetDateTime updatedAt;

    @PrePersist
    public void prePersist(){
        createdAt = OffsetDateTime.now();
        updatedAt = OffsetDateTime.now();
    }

    @PreUpdate
    public void  preUpdate(){
        updatedAt = OffsetDateTime.now();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloodCompatibilityEntity that = (BloodCompatibilityEntity) o;
        return Objects.equals(giver, that.giver) && Objects.equals(receiver, that.receiver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giver, receiver);
    }
}
