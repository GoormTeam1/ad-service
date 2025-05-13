package edu.goorm.ad_service.controller;

import edu.goorm.ad_service.dto.AdDto;
import edu.goorm.ad_service.service.AdService;
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
    public ResponseEntity<List<AdDto>> listAds() {
        return ResponseEntity.ok(adService.getAllAds());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdDto> getAd(@PathVariable Long id) {
        return ResponseEntity.ok(adService.getAd(id));
    }

    @PostMapping
    public ResponseEntity<AdDto> createAd(@RequestBody AdDto dto) {
        return ResponseEntity.ok(adService.createAd(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdDto> updateAd(@PathVariable Long id, @RequestBody AdDto dto) {
        return ResponseEntity.ok(adService.updateAd(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAd(@PathVariable Long id) {
        adService.deleteAd(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/view")
    public ResponseEntity<Void> increaseView(@PathVariable Long id) {
        adService.increaseViewCount(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/click")
    public ResponseEntity<Void> increaseClick(@PathVariable Long id) {
        adService.increaseClickCount(id);
        return ResponseEntity.ok().build();
    }

}
