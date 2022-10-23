package com.meetyourroommate.app.profile.application.transform;

import com.meetyourroommate.app.profile.application.transform.resources.ProfileResource;
import com.meetyourroommate.app.profile.domain.aggregates.Profile;
import com.meetyourroommate.app.shared.application.transform.EnhancedModelMapper;
import net.bytebuddy.build.ToStringPlugin;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

public class ProfileMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public ProfileResource toResource(Profile model){
       return mapper.map(model, ProfileResource.class);
    }

    public Profile toEntity(ProfileResource resource){
       return mapper.map(resource, Profile.class);
    }
}
