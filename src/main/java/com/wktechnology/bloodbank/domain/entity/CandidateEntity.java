package com.wktechnology.bloodbank.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.Set;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "CANDIDATES")
public class CandidateEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = "char(9)")
    private String rg;

    @Column(nullable = false, columnDefinition = "char(11)")
    private String cpf;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Enumerated(STRING)
    @Column(nullable = false)
    private SexEnum sex;

    @Column(nullable = false)
    private BigDecimal height;

    @Column(nullable = false)
    private BigDecimal weight;

    @Column(nullable = false)
    private String father;

    @Column(nullable = false)
    private String mother;

    @ManyToOne
    @JoinColumn(nullable = false, name = "blood_type_id")
    private BloodTypeEntity bloodType;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidate")
    private Set<ContactEntity> contacts;

    @ToString.Exclude
    @OneToMany(mappedBy = "candidate")
    private Set<AddressEntity> addressEntitySet;

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
        CandidateEntity that = (CandidateEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(rg, that.rg) &&
                Objects.equals(cpf, that.cpf) &&
                Objects.equals(birthdate, that.birthdate) &&
                sex == that.sex &&
                Objects.equals(height, that.height) &&
                Objects.equals(weight, that.weight) &&
                Objects.equals(father, that.father) &&
                Objects.equals(mother, that.mother) &&
                Objects.equals(bloodType, that.bloodType) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, rg, cpf, birthdate, sex, height, weight, father, mother, bloodType, createdAt, updatedAt);
    }

}
