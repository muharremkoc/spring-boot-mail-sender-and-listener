package com.example.springbootmailartemis.services;


import com.example.springbootmailartemis.dto.EmailRequestDTO;
import com.example.springbootmailartemis.dto.RequestFileDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface EmailService {
    String sendMail(EmailRequestDTO request, Map<String,String> model);
    String sendMailWithAttachment(EmailRequestDTO requestFileDTO,MultipartFile file);
    //ResponseEntity<Object> Upload(EmailRequestDTO requestDTO, MultipartFile file) throws IOException;

    String sendMailAttachment(RequestFileDTO requestFileDTO);
}
