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
@Table(name = "ADDRESSES")
public class AddressEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String number;

    @Column(nullable = false)
    private String district;

    @Column(nullable = false, columnDefinition = "char(8)")
    private String cep;

    @ManyToOne
    @JoinColumn(nullable = false)
    private CityEntity city = new CityEntity();

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
        AddressEntity that = (AddressEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(street, that.street) &&
                Objects.equals(number, that.number) &&
                Objects.equals(district, that.district) &&
                Objects.equals(cep, that.cep) &&
                Objects.equals(city, that.city) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(updatedAt, that.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, number, district, cep, city, createdAt, updatedAt);
    }
}
