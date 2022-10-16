package com.goodtime.webservice.data.repository;


import com.goodtime.webservice.data.vo.RolesAuthorizationVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface RoleAuthorizationRepository extends JpaRepository<RolesAuthorizationVO,Long> {

    @Query("SELECT r FROM RolesAuthorizationVO r WHERE r.name = :name")
    Optional<RolesAuthorizationVO> findByName(@Param("name") String name);
}
