package com.example.springbootmailartemis.controller;


import com.example.springbootmailartemis.dto.EmailRequestDTO;
import com.example.springbootmailartemis.dto.RequestFileDTO;
import com.example.springbootmailartemis.services.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailController {

    final EmailService emailService;

    @PostMapping("sendmail")
    public ResponseEntity<String> sendMail(@RequestBody EmailRequestDTO emailRequest) {
        Map<String, String> model = new HashMap<>();
        model.put("name", emailRequest.getName());
        model.put("value", "Welcome to Coding!!");
        String response = emailService.sendMail(emailRequest, model);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    /*
    @PostMapping(value = "sendAttachment",consumes = "multipart/form-data")
    public ResponseEntity<String> sendMailWithAttachment(@RequestBody EmailRequestDTO requestDTO,
                                                         @RequestPart("file") MultipartFile file, ModelMap modelMap) {
        Map<String, String> model = new HashMap<>();
        model.put("name", requestDTO.getName());
        model.put("value", "Welcome to Coding!!");
        String response = emailService.sendMailWithAttachment(requestDTO,file,model);
        modelMap.addAttribute("file", file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

     */

    @PostMapping(value = "/sendAttachment",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload( @RequestPart String to, @RequestPart MultipartFile file)  {

      EmailRequestDTO requestDTO=EmailRequestDTO.builder().to(to).from("kmuho5885").name("sdsdsadas").subject("sadasdasdas").build();

        return emailService.sendMailWithAttachment(requestDTO,file);
    }
    @PostMapping(value = "/sendAttachmentss",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String fileUpload( @RequestBody EmailRequestDTO requestDTO, @RequestPart final MultipartFile file)  {




        return emailService.sendMailWithAttachment(requestDTO,file);
    }

    @PostMapping(value = "/sendAttachs",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String files(@ModelAttribute RequestFileDTO requestFileDTO){
        return emailService.sendMailAttachment(requestFileDTO);
    }
}
