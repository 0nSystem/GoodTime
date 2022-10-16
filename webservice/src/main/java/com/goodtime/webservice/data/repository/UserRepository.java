package com.goodtime.webservice.data.repository;

import com.goodtime.webservice.data.vo.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface UserRepository extends JpaRepository<UserVO,Long> {

    @Query("SELECT u FROM UserVO u where u.username = :username")
    Optional<UserVO> findByUserName(@Param("username") String username);
}
