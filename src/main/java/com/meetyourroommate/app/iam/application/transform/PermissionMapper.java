package com.meetyourroommate.app.iam.application.transform;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.meetyourroommate.app.iam.application.transform.resources.PermissionResource;
import com.meetyourroommate.app.iam.domain.entities.Permission;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;

public class PermissionMapper {

  @Autowired
  EnhancedModelMapper mapper;

  public PermissionResource toResource(Permission model) {
    return mapper.map(model, PermissionResource.class);
  }

  public Page<PermissionResource> modelListToPage(List<Permission> modelList, Pageable pageable) {
    return new PageImpl<>(mapper.mapList(modelList, PermissionResource.class), pageable, modelList.size());
  }
}
