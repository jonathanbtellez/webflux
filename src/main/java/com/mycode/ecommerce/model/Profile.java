package com.mycode.ecommerce.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@ToString
@Document
@Getter
@Setter
@Builder
public class Profile {
    @Id
    private UUID id;
    private String name;
}
