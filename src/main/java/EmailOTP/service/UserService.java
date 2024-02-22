package EmailOTP.service;

import EmailOTP.entity.User;
import EmailOTP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
    private UserRepository userRepository;

public User registerUser(User user){
    return  userRepository.save(user);

}

    public User getUserByEmail(String email) {
        User byEmail = userRepository.findByEmail(email);
        return byEmail;
    }

    public void verifyEmail(User user) {
    user.setEmailVerified(true);
    userRepository.save(user);
    }
}
