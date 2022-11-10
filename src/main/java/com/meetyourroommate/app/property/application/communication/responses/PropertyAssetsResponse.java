package com.meetyourroommate.app.property.application.communication.responses;

import com.meetyourroommate.app.property.domain.entities.PropertyAsset;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

public class PropertyAssetsResponse extends BaseResponse<PropertyAsset> {
    public PropertyAssetsResponse(String message) {
        super(message);
    }

    public PropertyAssetsResponse(PropertyAsset resource) {
        super(resource);
    }
}
