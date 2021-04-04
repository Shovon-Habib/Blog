package com.dev.repo;

import com.dev.domain.User;
import com.dev.dto.projection.BloggerProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findByUserNameAndDomainStatus(String userName, boolean status);

    Optional<User> findByEmailAndDomainStatus(String userName, boolean status);

    Optional<User> findByIdAndDomainStatus(UUID id, boolean status);

    @Modifying
    @Query(value = "Update user set status =: status where id =: userId", nativeQuery = true)
    Integer updateUserStatus(@Param("userId") UUID id, @Param("status") boolean status);

    //As H2 directly does not support SP, I have to write native query
    @Query(value =
            "Select " +
            "u.status as bloggerStatus, " +
            "u.name as name, " +
            "CAST(u.id AS VARCHAR) as id " +
            "from user u " +
            "inner join user_role ur on u.id = ur.user_id and u.domain_status = true and ur.domain_status = true " +
            "inner join role r on ur.role_id = r.id and r. domain_status = true and r.role_name = 'Blogger' " +
            "limit :pageLimit offset (:page*:pageLimit)", nativeQuery = true)
    List<BloggerProjection> getAllBloggers(@Param("page") int page, @Param("pageLimit") int pageLimit);
}
