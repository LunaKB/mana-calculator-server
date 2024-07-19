package com.cmoncrieffe.spell_server.repository;

import com.cmoncrieffe.spell_server.entity.AffinityConflict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
public interface AffinityConflictRepository extends JpaRepository<AffinityConflict, Integer> { }
