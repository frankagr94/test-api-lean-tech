package com.leantech.test.persistence.entity;

import com.leantech.test.context.domain.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class that represents the position entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Entity(name = "positions")
@Table(name = "POSITIONS")
public class PositionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30, nullable = false)
    private String name;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "position")
    @OrderBy("salary DESC")
    private Set<EmployeeEntity> employees = new HashSet<>();

    public static PositionEntity fromPositionToPositionEntity(Position position) {
        return PositionEntity.builder()
                .id(position.getId())
                .name(position.getName())
                .employees(new HashSet<>())
                .build();
    }

    public static Position fromPositionEntityToPosition(PositionEntity positionEntity) {
        return Position.load(positionEntity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionEntity that = (PositionEntity) o;
        return id.equals(that.id) && Objects.equals(name, that.name) && Objects.equals(employees, that.employees);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, employees);
    }
}
