package com.meetyourroommate.app.property.application.communication.responses;

import com.meetyourroommate.app.property.domain.aggregates.Property;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class PropertyListResponse extends BaseResponse<List<Property>> {
    public PropertyListResponse(String message) {
        super(message);
    }

    public PropertyListResponse(List<Property> resource) {
        super(resource);
    }
}
