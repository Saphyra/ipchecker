package org.github.saphyra.ipchecker.dev;

import java.io.InputStream;
import java.util.Arrays;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NoOperationMailSender implements JavaMailSender {

    @Override
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void send(SimpleMailMessage... simpleMailMessages) throws MailException {
        throw new UnsupportedOperationException();
    }

    @Override
    public MimeMessage createMimeMessage() {
        return new MimeMessage((Session) null);
    }

    @Override
    public MimeMessage createMimeMessage(InputStream inputStream) throws MailException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void send(MimeMessage mimeMessage) throws MailException {
        boolean throwError = Math.random() < 0.5;
        if (throwError) {
            throw new MailSendException("Random exception thrown");
        } else {
            try {
                log.info("Sent mail to: {}", Arrays.toString(mimeMessage.getAllRecipients()));
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void send(MimeMessage... mimeMessages) throws MailException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void send(MimeMessagePreparator mimeMessagePreparator) throws MailException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void send(MimeMessagePreparator... mimeMessagePreparators) throws MailException {
        throw new UnsupportedOperationException();
    }
}
