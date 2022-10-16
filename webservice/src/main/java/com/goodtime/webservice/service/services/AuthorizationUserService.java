package com.goodtime.webservice.service.services;


import com.goodtime.webservice.data.repository.UserRepository;
import com.goodtime.webservice.data.vo.RolesAuthorizationVO;
import com.goodtime.webservice.data.vo.UserVO;
import com.goodtime.webservice.util.ManagerAttributesSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.servlet.http.HttpSession;

public class AuthorizationUserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private HttpSession httpSession;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVO userVO=userRepository.findByUserName(username).orElseThrow();
        final ManagerAttributesSession managerAttributesSession=new ManagerAttributesSession();
        managerAttributesSession.setId(userVO.getIdUser());

        final String[] role=userVO.getRolesAuthorization().stream().map(RolesAuthorizationVO::getName).toList().toArray(new String[userVO.getAuthorities().size()]);

        UserDetails userDetails= User.withUserDetails(userVO)
                .build();
        ManagerAttributesSession.setAttributesInHttpSession(managerAttributesSession,httpSession);
        return  userDetails;
    }
}
