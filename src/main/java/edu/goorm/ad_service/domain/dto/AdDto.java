package edu.goorm.ad_service.domain.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdDto {

    private Long id;
    private String title;
    private String imageUrl;
    private String linkUrl;
    private String status;
    private String type;
    private String description;
    private String startDate;
    private String endDate;
    private String orderer;
    private int viewCount;
    private int clickCount;
}
