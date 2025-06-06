package com.franqlinstore.franqlin_store.service;
import com.franqlinstore.franqlin_store.entity.AddressEntity;
import com.franqlinstore.franqlin_store.entity.UserEntity;
import com.franqlinstore.franqlin_store.repository.AddressRepository;
import com.franqlinstore.franqlin_store.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AddressService {

    private final AddressRepository addressRepository;

    private  final UserRepository userRepository;

    public AddressEntity saveAddress(Long userId, AddressEntity address) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        address.setUser(user);
        return addressRepository.save(address);
    }

    public List<AddressEntity> getAddressesByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElse(null);
        if (user == null) return null;

        return addressRepository.findByUser(user);
    }

    public boolean deleteAddress(Long addressId) {
        if (!addressRepository.existsById(addressId)) {
            return false;
        }
        try {
            addressRepository.deleteById(addressId);
            return true;
        } catch (Exception e) {
            // Optional: log error here
            return false;
        }
    }
}
