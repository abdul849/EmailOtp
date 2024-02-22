package EmailOTP.service;

import EmailOTP.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmailVerificationService {
    @Autowired
    private UserService userService;
    static final Map<String,String> emailOtpMapping = new HashMap<>();


    public Map<String,String> verifyOtp(String email,String otp){
        String s = emailOtpMapping.get(email);

        Map<String,String> response=new HashMap<>();

        if(s!=null && s.equals(otp)){
            User user=userService.getUserByEmail(email);

            if(user!=null){
                emailOtpMapping.remove(email);
                userService.verifyEmail(user);
                response.put("status","success");
                response.put("message","Email verify successfully");
            }else {
                response.put("status","error");
                response.put("message","User not found");
            }
        }else {
            response.put("status","error");
            response.put("message","invalid otp");
        }
        return response;
    }


}
