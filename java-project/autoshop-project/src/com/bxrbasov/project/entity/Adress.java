package com.bxrbasov.project.entity;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Adress {
    Integer adress_id;
    String country;
    String town;
}
