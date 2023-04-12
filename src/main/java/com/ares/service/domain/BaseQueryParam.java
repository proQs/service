package com.ares.service.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseQueryParam {
    private Integer start;
    private Integer pageSize;
    private String flagSortedByTime;

    public BaseQueryParam(Integer start, Integer pageSize) {
        this.start = start;
        this.pageSize = pageSize;
    }
}
