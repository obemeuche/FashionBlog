package com.example.fashionblog.service.serviceImpl;

import com.example.fashionblog.dto.LoginDto;
import com.example.fashionblog.dto.SignupDto;
import com.example.fashionblog.enums.Role;
import com.example.fashionblog.exception.UserAlreadyExist;
import com.example.fashionblog.exception.UserNotFound;
import com.example.fashionblog.model.User;
import com.example.fashionblog.repository.UserRepository;
import com.example.fashionblog.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustomerService {

    private final UserRepository userRepository;

    private final HttpSession httpSession;

    @Override
    public void signup(SignupDto signupDto) {
        String email = signupDto.getEmail();
        boolean user = userRepository.existsByEmail(email);

        if (user) {
            throw new UserAlreadyExist("User with " + email + " already exists");
        }

        User user1 = User.builder()
                .firstName(signupDto.getFirstName())
                .LastName(signupDto.getLastName())
                .email(signupDto.getEmail())
                .password(signupDto.getPassword())
                .role(Role.CUSTOMER)
                .comment(new ArrayList<>())
                .post(new ArrayList<>())
                .likedPost(new ArrayList<>())
                .build();

        userRepository.save(user1);
    }

    @Override
    public String login(LoginDto loginDto) {
        String userEmail = loginDto.getEmail();

        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new UserNotFound("User email or password not found!"));

        if (!loginDto.getPassword().equals(user.getPassword())) {
            return "User email or password is incorrect!";
        }

        httpSession.setAttribute("user_id", user.getId());

        return "Welcome onboard " + user.getFirstName() + " " + user.getLastName();
    }

    @Override
    public String logout() {
        httpSession.invalidate();
        return "Logout successful!";
    }

}
