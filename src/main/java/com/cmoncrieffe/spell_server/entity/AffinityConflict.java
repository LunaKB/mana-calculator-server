package com.cmoncrieffe.spell_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "affinity_conflicts")
@Getter
@Setter
public class AffinityConflict {
    @Id
    @Column(name = "number_of_aspects")
    private Integer numberOfAspects;

    @Column(name = "con_save_increase", updatable = false)
    private Integer conSaveIncrease;

    @Column(name = "benefit", updatable = false)
    private String benefitDescription;

    public AffinityConflict() {}

    @Override
    public String toString() {
        return "AffinityConflict{" +
                "numberOfAspects=" + numberOfAspects +
                ", conSaveIncrease=" + conSaveIncrease +
                ", benefitDescription='" + benefitDescription + '\'' +
                '}';
    }
}
