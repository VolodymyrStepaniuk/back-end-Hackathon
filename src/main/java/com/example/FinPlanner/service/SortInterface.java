package com.example.FinPlanner.service;

import org.springframework.data.domain.Sort;

public interface SortInterface {
    Sort sort(Sort.Direction direction, String properties);
}
