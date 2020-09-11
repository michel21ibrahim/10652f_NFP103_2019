package com.c2.hospital.equipmentservice.repository;

import com.c2.hospital.equipmentservice.model.ProviderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository extends JpaRepository<ProviderEntity, Integer> {

}
