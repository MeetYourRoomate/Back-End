package com.meetyourroommate.app.propertymanagement.domain.service;

import com.meetyourroommate.app.propertymanagement.domain.entities.PropertyAsset;
import com.meetyourroommate.app.propertymanagement.domain.valueobjects.PropertyAssetId;
import com.meetyourroommate.app.shared.services.CrudService;

public interface PropertyAssetService extends CrudService<PropertyAsset, Long> {
}
