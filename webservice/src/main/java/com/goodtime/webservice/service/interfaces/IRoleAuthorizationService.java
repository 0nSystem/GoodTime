package com.goodtime.webservice.service.interfaces;

import com.goodtime.webservice.service.dto.RoleAuthorizationDto;

public interface IRoleAuthorizationService {
    boolean insert(RoleAuthorizationDto roleAuthorizationDto);
    RoleAuthorizationDto findById(Long id);
}
