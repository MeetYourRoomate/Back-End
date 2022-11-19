package com.meetyourroommate.app.profile.infrastructure.persistance.jpa;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.profile.domain.entities.Atribute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtributeRepository extends JpaRepository<Atribute,String> {
}
