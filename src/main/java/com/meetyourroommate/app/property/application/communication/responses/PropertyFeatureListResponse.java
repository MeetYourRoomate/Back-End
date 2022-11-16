package com.meetyourroommate.app.property.application.communication.responses;

import com.meetyourroommate.app.property.domain.entities.PropertyFeature;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class PropertyFeatureListResponse extends BaseResponse<List<PropertyFeature>> {
    public PropertyFeatureListResponse(String message) {
        super(message);
    }

    public PropertyFeatureListResponse(List<PropertyFeature> resource) {
        super(resource);
    }
}
