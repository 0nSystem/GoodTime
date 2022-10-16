package com.goodtime.webservice.data.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "roles_authorization")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RolesAuthorizationVO implements GrantedAuthority {
    public static enum RoleType{
        USER("user"),ADMIN("admin");
        @Getter
        private final String name;
        RoleType(String name) {
            this.name=name;
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_role", nullable = false)
    private Long idRole;

   @Column(name = "role_name_authorization")
   private String name;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "assign_role_authorize",
            joinColumns = @JoinColumn(name = "id_role"),
            inverseJoinColumns = @JoinColumn(name = "id_user"))
   private Collection<UserVO> users=new ArrayList<>();
    @Override
    public String getAuthority() {
        return name;
    }
}
