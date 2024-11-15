package com.bankaio.Bankaio.Repository;

import com.bankaio.Bankaio.Entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {

}
