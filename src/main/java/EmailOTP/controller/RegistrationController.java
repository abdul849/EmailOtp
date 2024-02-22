package EmailOTP.controller;

import EmailOTP.entity.User;
import EmailOTP.service.EmailService;
import EmailOTP.service.EmailVerificationService;
import EmailOTP.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class RegistrationController {
    @Autowired
    private EmailVerificationService emailVerificationService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Map<String,String> registerUser(@RequestBody User user){
        //register the email withot verification
        User registerUser=userService.registerUser(user);

        //send otp email for email verification
        emailService.sendOtpEmail(user.getEmail());

        Map<String,String> response=new HashMap<>();
        response.put("status","success");
        response.put("message","User register successfully. chek your email for verification");
        return response;
    }
    @PostMapping("/verify-otp")
    public Map<String,String> verifyOtp(@RequestParam String email,@RequestParam String otp){
        return emailVerificationService.verifyOtp(email,otp);

    }
}
