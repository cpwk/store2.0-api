package com.maidao.edu.store.api.admin.repository;

import com.maidao.edu.store.api.admin.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role, Integer> {

}