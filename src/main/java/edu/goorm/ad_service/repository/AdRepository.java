package edu.goorm.ad_service.repository;

import edu.goorm.ad_service.dto.AdDto;
import edu.goorm.ad_service.entity.Ad;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository extends JpaRepository<Ad, Long> {
    
    List<AdDto> findByStatus(String status); // 광고 상태로 조회
    List<AdDto> findByOrderer(String orderer); // 광고주로 조회
    AdDto findByTitle(String title); // 광고 제목으로 조회

}
