package UJMProject.UJMProjectback.project.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {

    @Autowired
    private KakaoAuthService kakao;

    @ResponseBody
    @GetMapping("/kakaoAuth")
    public String kakaoCallback(@RequestParam String code){
        System.out.println("code : "+code);
        String accessToken = kakao.getKakaoAccessToken(code);
        System.out.println("accessToken = " + accessToken);
        HashMap<String, Object> userInfo = kakao.getUserInfo(accessToken);
        System.out.println(userInfo);
        return "OK";
    }
}
