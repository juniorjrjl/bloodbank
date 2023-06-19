package com.wktechnology.bloodbank.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
@ToString
public class BloodCompatibilityId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "giver_id")
    private Long giverId;

    @Column(name = "receiver_id")
    private Long receiverId;

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloodCompatibilityId that = (BloodCompatibilityId) o;
        return Objects.equals(giverId, that.giverId) && Objects.equals(receiverId, that.receiverId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(giverId, receiverId);
    }
}
