package com.franqlinstore.franqlin_store.service;
import com.franqlinstore.franqlin_store.entity.AdminEntity;
import com.franqlinstore.franqlin_store.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    public AdminEntity getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}
