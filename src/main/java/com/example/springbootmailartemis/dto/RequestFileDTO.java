package com.example.springbootmailartemis.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class RequestFileDTO {


    String from;
    String[] to;
    String subject;
    String name;
    List<MultipartFile> file;
}
