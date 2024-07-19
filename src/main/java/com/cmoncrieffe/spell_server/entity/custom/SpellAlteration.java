package com.cmoncrieffe.spell_server.entity.custom;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "custom_spell_base")
@Getter
@Setter
public class SpellAlteration {
    @EmbeddedId
    private SpellAlterationId id;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "casting_time")
    private String castingTime;

    @Column(name = "casting_range")
    private String range;

    @Column(name = "target")
    private Integer target;

    @Column(name = "area")
    private String area;

    @Column(name = "area_size")
    private String areaSize;

    @Column(name = "duration")
    private String duration;

    public SpellAlteration() {}

    public SpellAlteration(
            SpellAlterationId id, Integer cost, String castingTime, String range,
            Integer target, String area, String areaSize, String duration) {
        this.id = id;
        this.cost = cost;
        this.castingTime = castingTime;
        this.range = range;
        this.target = target;
        this.area = area;
        this.areaSize = areaSize;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "CustomSpellBase{" +
                "id=" + id.toString() +
                ", cost=" + cost +
                ", castingTime='" + castingTime + '\'' +
                ", range='" + range + '\'' +
                ", target=" + target +
                ", area='" + area + '\'' +
                ", areaSize='" + areaSize + '\'' +
                ", duration='" + duration + '\'' +
                '}';
    }
}
