package com.cmoncrieffe.spell_server.repository;

import com.cmoncrieffe.spell_server.entity.Caster;
import com.cmoncrieffe.spell_server.entity.custom.Spell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin
public interface SpellRepository extends JpaRepository<Spell, String> {
    List<Spell> findAllByOriginCaster(Caster originCaster);
}
