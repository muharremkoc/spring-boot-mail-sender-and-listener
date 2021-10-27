package com.example.springbootmailartemis.services;


import com.example.springbootmailartemis.dto.EmailRequestDTO;
import com.example.springbootmailartemis.dto.RequestFileDTO;
import com.example.springbootmailartemis.services.listener.DispatcherService;
import freemarker.template.Configuration;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailServiceImpl implements EmailService {


    final JavaMailSender mailSender;

    final DispatcherService dispatcherService;


    @Value( "${spring.mail.username}" )
    String userName;

    @Value("${spring.mail.password}")
    String password;

    @Value("${spring.mail.host")
    String host;

    @Override
    public String sendMail(EmailRequestDTO request, Map<String, String> model) {
        String response;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message);
            // ClassPathResource pdf = new ClassPathResource("static/attachment.pdf");

          //  Template template = configuration.getTemplate("email.ftl");
            //String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(request.getTo());
            helper.setFrom(request.getFrom());
            helper.setSubject(request.getSubject());
            helper.setText(request.getName());
            //helper.addAttachment("attachment.pdf", pdf);
            mailSender.send(message);
            response = "Email has been send to :" + request.getTo();
        } catch (MessagingException e) {
            response = "Email send failure to :" + request.getTo();
        }
        return response;
    }




    @Override
    public String sendMailWithAttachment(EmailRequestDTO requestDTO,MultipartFile file){


        String response;
        MimeMessage message = mailSender.createMimeMessage();


        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // ClassPathResource pdf = new ClassPathResource("static/attachment.pdf");


            //FileDataSource fds = new FileDataSource(file.getOriginalFilename());//file to attach
            helper.setTo(requestDTO.getTo());
            helper.setFrom(requestDTO.getFrom());
            helper.setSubject(requestDTO.getSubject());
            helper.setText(requestDTO.getName());





            helper.addAttachment(file.getOriginalFilename(),new ByteArrayResource(file.getBytes()));
            mailSender.send(message);

            response = "Email has been send  :"+requestDTO.getTo();
            dispatcherService.sendMessage("\nFrom:"+requestDTO.getFrom()+"\nTo:"+requestDTO.getTo()+"\n"+file.getOriginalFilename());
        } catch (MessagingException | IOException e) {
            response = "Email send failure!!";
        }
        return response;



    }

    @Override
    public String sendMailAttachment(RequestFileDTO requestFileDTO) {
        MimeMessage message = mailSender.createMimeMessage();
        //Get the uploaded files and store them
        List<MultipartFile> files = requestFileDTO.getFile();



                try {

                    MimeMessageHelper helper = new MimeMessageHelper(message, true);

                    helper.setTo(requestFileDTO.getTo());
                    helper.setFrom(requestFileDTO.getFrom());
                    helper.setSubject(requestFileDTO.getSubject());
                    helper.setText(requestFileDTO.getName());
                    for (MultipartFile multipartFile : files) {
                        helper.addAttachment(multipartFile.getOriginalFilename(), new ByteArrayResource(multipartFile.getBytes()));
                    }
                    mailSender.send(message);
                } catch (MessagingException | IOException e) {
                    e.printStackTrace();
                }


        // Here, you can save the product details in database

        return "viewProductDetail";
    }



/*
    @Override
    public String sendEmailwithAttachment(EmailRequestDTO request, MultipartFile file) {
        try {
            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper messageHelper =
                    new MimeMessageHelper(message, true);

            messageHelper.setFrom(request.getFrom());
            messageHelper.setTo(request.getTo());
            messageHelper.setSubject(request.getSubject());
            messageHelper.setText("Test Body");

            File myfile = new File(file.getOriginalFilename());

            messageHelper.addAttachment(myfile.getPath(),myfile);

            mailSender.send(message);

            return "Mail sent successfully";

        } catch (Exception e) {
            return "Mail sent failed";
        }
    }

 */

 /*
    public String sendMailWithAttachment(EmailRequestDTO requestDTO, MultipartFile file, Map<String,String> model){
        String response;
        MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());
            // ClassPathResource pdf = new ClassPathResource("static/attachment.pdf");
            File myFile = new File(file.getOriginalFilename());

            myFile.createNewFile();
            FileOutputStream fos =new FileOutputStream(myFile);
            fos.write(file.getBytes());
            fos.close();
            ClassPathResource image = new ClassPathResource(myFile.getName());
            Template template = configuration.getTemplate("email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
            helper.setTo(requestDTO.getTo());
            helper.setFrom(requestDTO.getFrom());
            helper.setSubject(requestDTO.getSubject());
            helper.setText(html, true);
            helper.addInline("deneme", image);
            //helper.addAttachment("attachment.pdf", pdf);
            mailSender.send(message);
            response = "Email has been sent to :" + requestDTO.getTo();
        } catch (MessagingException | IOException | TemplateException e) {
            response = "Email send failure to :" + requestDTO.getTo();
        }
        return response;
    }

  */
}
