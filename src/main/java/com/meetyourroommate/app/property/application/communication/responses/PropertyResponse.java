package com.meetyourroommate.app.property.application.communication.responses;

import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class PropertyResponse extends BaseResponse<Property> {
    public PropertyResponse(String message) {
        super(message);
    }

    public PropertyResponse(Property resource) {
        super(resource);
    }
}
