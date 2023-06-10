package com.bouali.register.services;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender mailSender;
  private final SpringTemplateEngine templateEngine;

  @Async
  public void send(
      String to,
      String username,
      String templateName,
      String confirmationUrl
  ) throws MessagingException {
    if (!StringUtils.hasLength(templateName)) {
      templateName = "confirm-email";
    }
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(
        mimeMessage,
        MimeMessageHelper.MULTIPART_MODE_MIXED,
        StandardCharsets.UTF_8.name()
    );
    Map<String, Object> properties = new HashMap<>();
    properties.put("username", username);
    properties.put("confirmationUrl", confirmationUrl);

    Context context = new Context();
    context.setVariables(properties);

    helper.setFrom("bouali.social@gmail.com");
    helper.setTo(to);
    helper.setSubject("Welcome to our nice platform");

    String template = templateEngine.process(templateName, context);

    helper.setText(template, true);

    mailSender.send(mimeMessage);
  }
}
