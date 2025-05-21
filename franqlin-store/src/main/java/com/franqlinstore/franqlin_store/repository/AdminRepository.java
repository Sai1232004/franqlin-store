package com.franqlinstore.franqlin_store.repository;
import com.franqlinstore.franqlin_store.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findByEmail(String email);
}
