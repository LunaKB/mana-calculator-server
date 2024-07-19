package com.cmoncrieffe.spell_server.entity.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CasterDto {
    private String uuid;
    private String name;
    private Integer manaPoints;
    private Integer mind;
    private Integer source;
    private Integer will;

    public CasterDto(String uuid, String name, Integer manaPoints, Integer mind, Integer source, Integer will) {
        this.uuid = uuid;
        this.name = name;
        this.manaPoints = manaPoints;
        this.mind = mind;
        this.source = source;
        this.will = will;
    }

    @Override
    public String toString() {
        return "CasterDTO{" +
                "uuid='" + uuid + '\'' +
                ", name='" + name + '\'' +
                ", manaPoints=" + manaPoints +
                ", mind=" + mind +
                ", source=" + source +
                ", will=" + will +
                '}';
    }
}
