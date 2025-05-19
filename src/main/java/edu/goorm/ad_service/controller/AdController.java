package edu.goorm.ad_service.controller;

import edu.goorm.ad_service.dto.AdDto;
import edu.goorm.ad_service.logger.CustomLogger;
import edu.goorm.ad_service.service.AdService;
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

        CustomLogger.logRequest(
                "GET_AD_LIST",
                "/admin/ads",
                "GET",
                userEmail,
                null,
                request
        );
        return ResponseEntity.ok(adService.getAllAds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdDto> getAd(
            @PathVariable Long id,
            @RequestHeader(value = "X-User-Email", required = false) String userEmail,
            HttpServletRequest request) {

        CustomLogger.logRequest(
                "GET_AD",
                "/admin/ads/" + id,
                "GET",
                userEmail,
                null,
                request
        );
        return ResponseEntity.ok(adService.getAd(id));
    }

    @PostMapping
    public ResponseEntity<AdDto> createAd(
            @RequestBody AdDto dto,
            @RequestHeader(value = "X-User-Email", required = false) String userEmail,
            HttpServletRequest request) {

        CustomLogger.logRequest(
                "CREATE_AD",
                "/admin/ads",
                "POST",
                userEmail,
                dto.toString(),
                request
        );
        return ResponseEntity.ok(adService.createAd(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(
            @PathVariable Long id,
            @RequestBody AdDto dto,
            @RequestHeader(value = "X-User-Email", required = false) String userEmail,
            HttpServletRequest request) {

        CustomLogger.logRequest(
                "UPDATE_AD",
                "/admin/ads/" + id,
                "PUT",
                userEmail,
                dto.toString(),
                request
        );
        return ResponseEntity.ok(adService.updateAd(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(
            @PathVariable Long id,
            @RequestHeader(value = "X-User-Email", required = false) String userEmail,
            HttpServletRequest request) {

        adService.deleteAd(id);

        CustomLogger.logRequest(
                "DELETE_AD",
                "/admin/ads/" + id,
                "DELETE",
                userEmail,
                null,
                request
        );
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/view")
    public ResponseEntity<Void> increaseView(
            @PathVariable Long id,
            @RequestHeader(value = "X-User-Email", required = false) String userEmail,
            HttpServletRequest request) {

        adService.increaseViewCount(id);

        CustomLogger.logRequest(
                "INCREASE_AD_VIEW",
                "/admin/ads/" + id + "/view",
                "PATCH",
                userEmail,
                null,
                request
        );
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/click")
    public ResponseEntity<Void> increaseClick(
            @PathVariable Long id,
            @RequestHeader(value = "X-User-Email", required = false) String userEmail,
            HttpServletRequest request) {

        adService.increaseClickCount(id);

        CustomLogger.logRequest(
                "INCREASE_AD_CLICK",
                "/admin/ads/" + id + "/click",
                "PATCH",
                userEmail,
                null,
                request
        );
        return ResponseEntity.ok().build();
    }
}
