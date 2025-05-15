package edu.goorm.ad_service.service;

import edu.goorm.ad_service.dto.AdDto;
import edu.goorm.ad_service.entity.Ad;
import edu.goorm.ad_service.repository.AdRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdService {

    private final AdRepository adRepository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<AdDto> getAllAds() {
        return adRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public AdDto getAd(Long id) {
        Ad ad = adRepository.findById(id).orElseThrow();
        return toDto(ad);
    }

    @Transactional
    public AdDto createAd(AdDto dto) {
        Ad ad = toEntity(dto);
        ad.setViewCount(0);
        ad.setClickCount(0);
        return toDto(adRepository.save(ad));
    }

    @Transactional
    public AdDto updateAd(Long id, AdDto dto) {
        Ad ad = adRepository.findById(id).orElseThrow();
        ad.setTitle(dto.getTitle());
        ad.setImageUrl(dto.getImageUrl());
        ad.setLinkUrl(dto.getLinkUrl());
        ad.setDescription(dto.getDescription());
        ad.setType(dto.getType());
        ad.setStatus(dto.getStatus());
        ad.setStartDate(LocalDateTime.parse(dto.getStartDate(), formatter));
        ad.setEndDate(LocalDateTime.parse(dto.getEndDate(), formatter));
        ad.setOrderer(dto.getOrderer());
        return toDto(adRepository.save(ad));
    }

    @Transactional
    public void deleteAd(Long id) {
        adRepository.deleteById(id);
    }

    private AdDto toDto(Ad ad) {
        return AdDto.builder()
                .id(ad.getId())
                .title(ad.getTitle())
                .imageUrl(ad.getImageUrl())
                .linkUrl(ad.getLinkUrl())
                .description(ad.getDescription())
                .type(ad.getType())
                .status(ad.getStatus())
                .startDate(ad.getStartDate().format(formatter))
                .endDate(ad.getEndDate().format(formatter))
                .orderer(ad.getOrderer())
                .viewCount(ad.getViewCount())
                .clickCount(ad.getClickCount())
                .build();
    }

    private Ad toEntity(AdDto dto) {
        return Ad.builder()
                .title(dto.getTitle())
                .imageUrl(dto.getImageUrl())
                .linkUrl(dto.getLinkUrl())
                .description(dto.getDescription())
                .type(dto.getType())
                .status(dto.getStatus())
                .startDate(LocalDateTime.parse(dto.getStartDate(), formatter))
                .endDate(LocalDateTime.parse(dto.getEndDate(), formatter))
                .orderer(dto.getOrderer())
                .viewCount(dto.getViewCount())
                .clickCount(dto.getClickCount())
                .build();
    }

    @Transactional
    public void increaseViewCount(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow();
        ad.setViewCount(ad.getViewCount() + 1);
        adRepository.save(ad);
    }

    @Transactional
    public void increaseClickCount(Long adId) {
        Ad ad = adRepository.findById(adId).orElseThrow();
        ad.setClickCount(ad.getClickCount() + 1);
        adRepository.save(ad);
    }

}
