package com.meetyourroommate.app.roommate.application.services;

import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.roommate.domain.entities.RoommateRequest;
import com.meetyourroommate.app.shared.application.services.CrudService;

import java.util.List;

public interface RoommateRequestService extends CrudService<RoommateRequest, Long> {
    List<RoommateRequest> findAllByStudentRequestor(Profile studentRequestor) throws Exception;
    List<RoommateRequest> findAllByStudentRequested(Profile studentRequested) throws Exception;
}
