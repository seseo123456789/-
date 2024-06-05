package com.green.Team3.admin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SalesVO {
    private String label;
    private String type;
    private String backgroundColor;
    private String borderColor;
    private double[] data;
}
