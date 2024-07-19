package com.cmoncrieffe.spell_server.repository;

import com.cmoncrieffe.spell_server.entity.custom.SpellAlteration;
import com.cmoncrieffe.spell_server.entity.custom.SpellAlterationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface SpellAlterationRepository extends JpaRepository<SpellAlteration, SpellAlterationId> { }
