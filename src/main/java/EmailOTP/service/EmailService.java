package EmailOTP.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

import static EmailOTP.service.EmailVerificationService.emailOtpMapping;

@Service
public class EmailService {
@Autowired
private JavaMailSender javaMailSender;

private final UserService userService;
    public EmailService(UserService userService) {
        this.userService = userService;
    }


    public String generateOtp(){
        return String.format("%06d",new java.util.Random().nextInt(10000));
    }



    public void sendOtpEmail(String email) {
        String otp= generateOtp();

          //save the otp for later verification
        emailOtpMapping.put(email,otp);
        sendEmail(email,"otp for email verification","your otp is:"+otp);

    }
private void sendEmail(String to,String subject,String text){
    SimpleMailMessage message= new SimpleMailMessage();
    message.setFrom("your.gmail@gmail.com");
    message.setTo(to);
    message.setSubject(subject);
    message.setText(text);
    javaMailSender.send(message);
}



}
