package edu.goorm.ad_service.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "ads")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;             // 광고 제목
    private String imageUrl;          // 이미지 URL
    private String linkUrl;           // 클릭 시 이동할 링크
    private String description;       // 광고 설명
    private String status;          // 광고 상태 (ACTIVE, INACTIVE 등)

    private LocalDateTime startDate;  // 광고 시작일
    private LocalDateTime endDate;    // 광고 종료일

    private String orderer;           // 광고주 정보

    private int viewCount;            // 노출 수
    private int clickCount;           // 클릭 수
    
}
