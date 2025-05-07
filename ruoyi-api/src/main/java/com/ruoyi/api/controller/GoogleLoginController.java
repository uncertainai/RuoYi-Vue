package com.ruoyi.api.controller;


// import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
// import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
// import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
// import com.google.api.client.http.javanet.NetHttpTransport;
// import com.google.api.client.json.jackson2.JacksonFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Controller;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.RequestParam;

// import javax.servlet.http.HttpServletResponse;
// import java.io.IOException;
// import java.util.Collections;

// @Controller
public class GoogleLoginController {

    // @Value("${google.clientId}")
    // private String clientId;

    // @Value("${google.clientSecret}")
    // private String clientSecret;

    // @Value("${google.redirectUri}")
    // private String redirectUri;

    // private static final JacksonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    // private static final NetHttpTransport HTTP_TRANSPORT = new NetHttpTransport();

    // @GetMapping("/login")
    // public void login(HttpServletResponse response) throws IOException {
    //     // 构造Google授权URL，跳转用户去谷歌登录授权
    //     String url = "https://accounts.google.com/o/oauth2/v2/auth" +
    //             "?client_id=" + clientId +
    //             "&response_type=code" +
    //             "&scope=openid%20email%20profile" +
    //             "&redirect_uri=" + redirectUri +
    //             "&access_type=offline";
    //     response.sendRedirect(url);
    // }

    // @GetMapping("/oauth2callback")
    // public String oauth2callback(@RequestParam("code") String code) throws Exception {
    //     // 用code换取token
    //     GoogleAuthorizationCodeTokenRequest tokenRequest = new GoogleAuthorizationCodeTokenRequest(
    //             HTTP_TRANSPORT, JSON_FACTORY,
    //             "https://oauth2.googleapis.com/token",
    //             clientId, clientSecret, code, redirectUri);

    //     var tokenResponse = tokenRequest.execute();

    //     // 验证ID Token
    //     GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(HTTP_TRANSPORT, JSON_FACTORY)
    //             .setAudience(Collections.singletonList(clientId))
    //             .build();

    //     GoogleIdToken idToken = verifier.verify(tokenResponse.getIdToken());
    //     if (idToken != null) {
    //         GoogleIdToken.Payload payload = idToken.getPayload();

    //         String userId = payload.getSubject();  // 用户唯一ID
    //         String email = payload.getEmail();
    //         boolean emailVerified = Boolean.TRUE.equals(payload.getEmailVerified());
    //         String name = (String) payload.get("name");
    //         String pictureUrl = (String) payload.get("picture");

    //         // 这里你可以把用户信息保存到数据库，或者创建session等
    //         System.out.println("User ID: " + userId);
    //         System.out.println("Email: " + email);
    //         System.out.println("Name: " + name);

    //         return "login_success";  // 返回一个登录成功页面
    //     } else {
    //         return "login_failure";  // 验证失败页面
    //     }
    // }
}