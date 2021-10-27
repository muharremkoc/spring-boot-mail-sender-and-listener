package com.example.springbootmailartemis.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmailRequestDTO {

    String from;
    String to;
    String subject;
    String name;

}
