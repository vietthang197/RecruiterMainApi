package com.recruitercorp.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
public class JobDto implements Serializable {
    private String id;
    private String title;
    private String description;
    private String salaryFrom;
    private String salaryTo;
    private String reward;
    private String tag;
}
