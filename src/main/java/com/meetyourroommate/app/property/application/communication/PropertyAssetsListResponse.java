package com.meetyourroommate.app.property.application.communication;

import com.meetyourroommate.app.property.domain.entities.PropertyAsset;
import com.meetyourroommate.app.shared.application.communication.BaseResponse;

import java.util.List;

public class PropertyAssetsListResponse extends BaseResponse<List<PropertyAsset>> {
    public PropertyAssetsListResponse(String message) {
        super(message);
    }

    public PropertyAssetsListResponse(List<PropertyAsset> resource) {
        super(resource);
    }
}
