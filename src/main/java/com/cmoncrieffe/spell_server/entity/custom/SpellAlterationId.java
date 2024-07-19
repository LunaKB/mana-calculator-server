package com.cmoncrieffe.spell_server.entity.custom;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Embeddable
@Getter
@Setter
public class SpellAlterationId implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn (name = "spell_id", referencedColumnName = "uuid", nullable = false)
    private Spell spell;

    public SpellAlterationId() { }

    public SpellAlterationId(Spell spell) {
        this.spell = spell;
    }
}
