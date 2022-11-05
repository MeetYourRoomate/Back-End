package com.meetyourroommate.app.roommate.application.services;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.roommate.domain.entities.Roommate;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.Optional;

public interface RoommateService extends CrudService<Roommate, Long> {

    Optional<Roommate> getRoommateByProfile(Profile profile) throws Exception;
}
