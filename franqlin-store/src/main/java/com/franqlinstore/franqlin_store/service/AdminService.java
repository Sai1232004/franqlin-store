package com.franqlinstore.franqlin_store.service;
import com.franqlinstore.franqlin_store.entity.AdminEntity;
import com.franqlinstore.franqlin_store.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {

    private  final AdminRepository adminRepository;

    public AdminEntity getAdminByEmail(String email) {
        return adminRepository.findByEmail(email);
    }
}