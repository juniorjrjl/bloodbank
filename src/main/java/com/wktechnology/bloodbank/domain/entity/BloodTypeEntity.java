package com.wktechnology.bloodbank.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
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
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "BLOOD_TYPES")
public class BloodTypeEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "bloodType")
    @ToString.Exclude
    private Set<CandidateEntity> candidates;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "BLOOD_COMPATIBILITY",
            joinColumns = @JoinColumn(name = "giver"),
            inverseJoinColumns = @JoinColumn(name = "receiver"))
    private Set<BloodTypeEntity> givers = new HashSet<>();

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "BLOOD_COMPATIBILITY",
            joinColumns = @JoinColumn(name = "receiver"),
            inverseJoinColumns = @JoinColumn(name = "giver"))
    private Set<BloodTypeEntity> receivers = new HashSet<>();

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
        BloodTypeEntity that = (BloodTypeEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createdAt, updatedAt);
    }

}
