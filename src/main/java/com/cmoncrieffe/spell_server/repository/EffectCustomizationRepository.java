package com.cmoncrieffe.spell_server.repository;

import com.cmoncrieffe.spell_server.entity.custom.EffectCustomization;
import com.cmoncrieffe.spell_server.entity.custom.EffectCustomizationId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface EffectCustomizationRepository extends JpaRepository<EffectCustomization, EffectCustomizationId> { }
