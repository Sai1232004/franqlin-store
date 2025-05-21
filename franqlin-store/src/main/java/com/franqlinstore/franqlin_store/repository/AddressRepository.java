package com.franqlinstore.franqlin_store.repository;
import com.franqlinstore.franqlin_store.entity.AddressEntity;
import com.franqlinstore.franqlin_store.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    List<AddressEntity> findByUser(UserEntity user);
}
