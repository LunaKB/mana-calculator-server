package com.cmoncrieffe.spell_server.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpellAlterationDto {
    private String spellId;
    private Integer cost;
    private String castingTime;
    private String range;
    private Integer target;
    private String area;
    private String areaSize;
    private String duration;

    public SpellAlterationDto(
            String spellId, Integer cost, String castingTime, String range,
            Integer target, String area, String areaSize, String duration) {
        this.spellId = spellId;
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
        return "SpellAlterationDto{" +
                "spellId='" + spellId + '\'' +
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
