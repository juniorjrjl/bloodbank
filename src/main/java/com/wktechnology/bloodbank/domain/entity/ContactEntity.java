package com.wktechnology.bloodbank.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CONTACTS")
public class ContactEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "candidate_id", nullable = false)
    @ToString.Exclude
    private CandidateEntity candidate = new CandidateEntity();

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
        ContactEntity entity = (ContactEntity) o;
        return Objects.equals(id, entity.id) &&
                Objects.equals(type, entity.type) &&
                Objects.equals(value, entity.value) &&
                Objects.equals(createdAt, entity.createdAt) &&
                Objects.equals(updatedAt, entity.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, value, createdAt, updatedAt);
    }
}
