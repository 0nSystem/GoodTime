package com.goodtime.webservice.service.interfaces;

import com.goodtime.webservice.service.dto.UserDto;
import com.goodtime.webservice.service.dto.UserRegisterDto;

public interface IUserService {
    boolean insert(UserRegisterDto userDto);
    boolean update(UserDto userDto);
    boolean delete(UserDto userDto);
    UserDto findById(Long id);

}
