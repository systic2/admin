package com.example.admin.model;

import lombok.Data;

@Data
public class SearchParam {
    private String account;
    private String email;
    private int page;
}
