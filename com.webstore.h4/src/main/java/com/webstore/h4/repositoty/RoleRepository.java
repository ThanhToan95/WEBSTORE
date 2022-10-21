package com.webstore.h4.repositoty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webstore.h4.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

}
