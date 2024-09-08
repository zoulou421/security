package com.groupekilo.security.dto;

import java.util.List;

public class PaginatedResult<T> {
    private List<T> results;
    private int totalPages;

    public PaginatedResult(List<T> results, int totalPages) {
        this.results = results;
        this.totalPages = totalPages;
    }

    public List<T> getResults() {
        return results;
    }

    public int getTotalPages() {
        return totalPages;
    }
}

