package com.company.docreview.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Setter
@Getter
public class ReviewDTO {
    private Long id;
    private String comment;
    private BigDecimal overallRating;
    private OffsetDateTime createdAt;
    private String doctorName;
    private String userName;
    private Long userId; // ✅ Add this field

}