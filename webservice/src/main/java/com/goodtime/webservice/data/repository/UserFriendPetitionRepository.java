package com.goodtime.webservice.data.repository;

import com.goodtime.webservice.data.vo.UserFriendPetitionVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserFriendPetitionRepository extends JpaRepository<UserFriendPetitionVO,Long> {

    @Query("select up from UserFriendPetitionVO up " +
            "where up.idUserReceived=:idReceived and up.idUserSend=:idSender")
    Optional<UserFriendPetitionVO> findBySenderAndReceiver(@Param("idReceived") long idReceived, @Param("idSender") long idSender);

    @Query("select up from UserFriendPetitionVO up " +
            "where (up.idUserReceived=:idUser or up.idUserSend=:idUser) " +
            "and up.accepted = 1 " +
            "and up.decline <> 1 ")
    List<UserFriendPetitionVO> findAllPetitionWithUserAcceptedAndNotDecline(@Param("idUser") Long idUser);

    @Query("select up from UserFriendPetitionVO up " +
            "where up.idUserReceived=:idReceived " +
            "and up.idUserSend=:idSender " +
            "and up.decline <> 1 " +
            "and up.accepted <> 1 ")
    Optional<UserFriendPetitionVO> findBySenderAndReceiverToAcceptAndNotDecline(@Param("idReceived") long idReceived, @Param("idSender") long idSender);

    @Query("select up from UserFriendPetitionVO up " +
            "where up.idUserReceived=:idUser " +
            "and up.decline <> 1 " +
            "and up.accepted <> 1 ")
    List<UserFriendPetitionVO> findByUserReceivedToEarringAcceptAndNotDecline(@Param("idUser") long idUser);

    @Query("select up from UserFriendPetitionVO up " +
            "where (up.idUserReceived=:idUser or up.idUserSend=:idUser) " +
            "and up.decline <> 1 " +
            "and up.accepted <> 1 ")
    List<UserFriendPetitionVO> findByUserToEarringAcceptAndNotDecline(@Param("idUser") long idUser);

}
