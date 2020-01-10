package com.maidao.edu.store.api.admin.repository;


import com.maidao.edu.store.api.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByUsername(String username);

}