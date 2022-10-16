package com.goodtime.webservice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleAuthorizationDto {
    private Long idRole;
    private String role;
}
