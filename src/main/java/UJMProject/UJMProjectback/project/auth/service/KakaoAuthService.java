package UJMProject.UJMProjectback.project.auth.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

@Service
public class KakaoAuthService {

    public String getKakaoAccessToken(String code){
        String access_Token ="";
        String refresh_Token="";
        String reqURL="https://kauth.kakao.com/oauth/token";

        try{
            URL url =new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb=new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=17178c0a2d3d54cd7f4450834fe946b4");
            sb.append("&redirect_uri=http://localhost:3000/kakaoAuth");
            sb.append("&code="+code);
            bw.write(sb.toString());
            bw.flush();

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result ="";
            while((line=br.readLine())!=null){
                result +=line;
            }
            System.out.println("response body : "+result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_Token = " + access_Token);
            System.out.println("refresh_Token = " + refresh_Token);

            br.close();
            bw.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return access_Token;
    }

    public HashMap<String, Object> getUserInfo (String accessToken){
        HashMap<String, Object> userInfo = new HashMap<>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Authorization", "Bearer "+accessToken);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line= "";
            String result = "";

            while ((line = br.readLine())!=null){
                result+=line;
            }
            System.out.println("response Body = " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            long id = element.getAsJsonObject().get("id").getAsLong();
            String nickname = element.getAsJsonObject().get("properties").getAsJsonObject().getAsJsonObject().get("nickname").getAsString();
            boolean hasGender =element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_gender").getAsBoolean();
            boolean hasEmail = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("has_email").getAsBoolean();
            String email = "";
            String gender = "";
            if(hasEmail){
                email = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("email").getAsString();
            }
            if(hasGender){
                gender = element.getAsJsonObject().get("kakao_account").getAsJsonObject().get("gender").getAsString();
            }
            else{
                System.out.println("asdasd");
            }

            userInfo.put("id",id);
            userInfo.put("nickname",nickname);
            userInfo.put("gender",gender);
            userInfo.put("email",email);

            br.close();

        }
        catch(IOException e){
            e.printStackTrace();
        }
        return userInfo;
    }
}
