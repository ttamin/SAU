package com.example.sau.dto;

import lombok.Data;

@Data
public class BlogDto {
    private Long id;
    private String name;
    private String fulltext;
    private String imageName;
    private String anons;

}
