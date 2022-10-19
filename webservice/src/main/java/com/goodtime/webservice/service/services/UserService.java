package com.goodtime.webservice.service.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goodtime.webservice.data.repository.RoleAuthorizationRepository;
import com.goodtime.webservice.data.repository.UserRepository;
import com.goodtime.webservice.data.vo.RolesAuthorizationVO;
import com.goodtime.webservice.data.vo.UserVO;
import com.goodtime.webservice.service.dto.UserDto;
import com.goodtime.webservice.service.dto.UserRegisterDto;
import com.goodtime.webservice.service.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleAuthorizationRepository roleAuthorizationRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean insert(UserRegisterDto userDto) {
        if(userRepository.findByUserName(userDto.getUsername()).isPresent())
            return false;
        UserVO userVO=objectMapper.convertValue(userDto,UserVO.class);
        RolesAuthorizationVO rolesAuthorizationVO=roleAuthorizationRepository
                .findByName(RolesAuthorizationVO.RoleType.USER.getName()).orElseThrow();

        userVO.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userVO.setRolesAuthorization(Set.of(rolesAuthorizationVO));
        userVO.setAccountNonLocked(true);
        userVO.setAccountNonExpired(true);
        userVO.setCredentialsNonExpired(true);
        userVO.setEnabled(true);
        userRepository.save(userVO);
        return true;
    }

    @Override
    public boolean update(UserDto userDto) {
        return false;
    }

    @Override
    public boolean delete(UserDto userDto) {
        return false;
    }

    @Override
    public UserDto findById(Long id) {
        return objectMapper.convertValue(userRepository.findById(id).orElseThrow(),
                UserDto.class);
    }

    @Override
    public UserDto findByUserName(String name) {
        return objectMapper.convertValue(
                userRepository.findByUserName(name).orElseThrow(),
                UserDto.class);
    }
}
