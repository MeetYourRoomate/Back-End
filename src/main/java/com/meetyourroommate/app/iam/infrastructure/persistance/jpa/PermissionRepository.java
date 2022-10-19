package com.meetyourroommate.app.iam.infrastructure.persistance.jpa;

import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
  Optional<Permission> findByName(PermissionName name);
}
  
