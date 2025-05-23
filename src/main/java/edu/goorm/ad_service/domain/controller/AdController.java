package edu.goorm.ad_service.domain.controller;

import edu.goorm.ad_service.domain.dto.AdDto;
import edu.goorm.ad_service.global.logger.CustomLogger;
import edu.goorm.ad_service.domain.service.AdService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/ads")
@RequiredArgsConstructor
public class AdController {

    private final AdService adService;

    @GetMapping
    public ResponseEntity<List<AdDto>> listAds(
        @RequestHeader(value = "X-User-Email", required = false) String userEmail,
        HttpServletRequest request) {

        long start = System.currentTimeMillis();
        List<AdDto> ads = adService.getAllAds();
        CustomLogger.logRequest(
            "GET_AD_LIST",
            "/admin/ads",
            "GET",
            userEmail,
            request,
            200,
            System.currentTimeMillis() - start
        );
        return ResponseEntity.ok(ads);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdDto> getAd(
        @PathVariable Long id,
        @RequestHeader(value = "X-User-Email", required = false) String userEmail,
        HttpServletRequest request) {

        long start = System.currentTimeMillis();
        AdDto ad = adService.getAd(id);
        CustomLogger.logRequest(
            "GET_AD",
            "/admin/ads/" + id,
            "GET",
            userEmail,
            request,
            200,
            System.currentTimeMillis() - start
        );
        return ResponseEntity.ok(ad);
    }

    @PostMapping
    public ResponseEntity<AdDto> createAd(
        @RequestBody AdDto dto,
        @RequestHeader(value = "X-User-Email", required = false) String userEmail,
        HttpServletRequest request) {

        long start = System.currentTimeMillis();
        AdDto result = adService.createAd(dto);
        CustomLogger.logRequest(
            "CREATE_AD",
            "/admin/ads",
            "POST",
            dto.toString(),
            request,
            200,
            System.currentTimeMillis() - start
        );
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(
        @PathVariable Long id,
        @RequestBody AdDto dto,
        @RequestHeader(value = "X-User-Email", required = false) String userEmail,
        HttpServletRequest request) {

        long start = System.currentTimeMillis();
        AdDto result = adService.updateAd(id, dto);
        CustomLogger.logRequest(
            "UPDATE_AD",
            "/admin/ads/" + id,
            "PUT",
            dto.toString(),
            request,
            200,
            System.currentTimeMillis() - start
        );
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(
        @PathVariable Long id,
        @RequestHeader(value = "X-User-Email", required = false) String userEmail,
        HttpServletRequest request) {

        long start = System.currentTimeMillis();
        adService.deleteAd(id);
        CustomLogger.logRequest(
            "DELETE_AD",
            "/admin/ads/" + id,
            "DELETE",
            userEmail,
            request,
            200,
            System.currentTimeMillis() - start
        );
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/view")
    public ResponseEntity<Void> increaseView(
        @PathVariable Long id,
        @RequestHeader(value = "X-User-Email", required = false) String userEmail,
        HttpServletRequest request) {

        long start = System.currentTimeMillis();
        adService.increaseViewCount(id);
        CustomLogger.logRequest(
            "INCREASE_AD_VIEW",
            "/admin/ads/" + id + "/view",
            "PATCH",
            userEmail,
            request,
            200,
            System.currentTimeMillis() - start
        );
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/click")
    public ResponseEntity<Void> increaseClick(
        @PathVariable Long id,
        @RequestHeader(value = "X-User-Email", required = false) String userEmail,
        HttpServletRequest request) {

        long start = System.currentTimeMillis();
        adService.increaseClickCount(id);
        CustomLogger.logRequest(
            "INCREASE_AD_CLICK",
            "/admin/ads/" + id + "/click",
            "PATCH",
            userEmail,
            request,
            200,
            System.currentTimeMillis() - start
        );
        return ResponseEntity.ok().build();
    }
}