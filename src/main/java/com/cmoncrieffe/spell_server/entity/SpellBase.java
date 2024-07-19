package com.cmoncrieffe.spell_server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "spell_bases")
@Getter
@Setter
public class SpellBase {
    @Id
    @Column(name = "cost")
    private Integer cost;

    @Column(name = "casting_time", updatable = false)
    private String castingTime;

    @Column(name = "casting_range", updatable = false)
    private String range;

    @Column(name = "target", updatable = false)
    private Integer target;

    @Column(name = "area", updatable = false)
    private String area;

    @Column(name = "area_size", updatable = false)
    private String areaSize;

    @Column(name = "duration", updatable = false)
    private String duration;

    public SpellBase() {}

    @Override
    public String toString() {
        return "SpellBase{" +
                "cost=" + cost +
                ", castingTime='" + castingTime + '\'' +
                ", range='" + range + '\'' +
                ", target=" + target +
                ", area='" + area + '\'' +
                ", area_size='" + areaSize + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
