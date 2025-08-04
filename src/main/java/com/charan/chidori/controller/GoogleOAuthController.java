//package com.charan.chidori.controller;
//
//
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.*;
//import org.springframework.util.LinkedMultiValueMap;
//import org.springframework.util.MultiValueMap;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.Map;
//
//@RestController
//public class GoogleOAuthController {
//
//    @Value("${spring.security.oauth2.client.registration.google.client-id:client}")
//    private String clientId;
//
//    @Value("${spring.security.oauth2.client.registration.google.client-secret:secret}")
//    private String clientSecret;
//
//    @Value("${spring.security.oauth2.client.registration.google.redirect-uri:uri}")
//    private String redirectUri;
//
//    private final RestTemplate restTemplate = new RestTemplate();
//
//    @GetMapping("/auth/google")
//    public void redirectToGoogle(HttpServletResponse response) throws IOException {
//        String authUrl = "https://accounts.google.com/o/oauth2/v2/auth"
//                + "?response_type=code"
//                + "&client_id=" + URLEncoder.encode(clientId, StandardCharsets.UTF_8)
//                + "&redirect_uri=" + URLEncoder.encode(redirectUri, StandardCharsets.UTF_8)
//                + "&scope=" + URLEncoder.encode("openid email profile", StandardCharsets.UTF_8)
//                + "&access_type=offline"
//                + "&prompt=consent";
//
//        response.sendRedirect(authUrl);
//    }
//
//    @GetMapping("/login/oauth2/code/google")
//    public ResponseEntity<?> handleGoogleCallback(@RequestParam("code") String code) {
//        String tokenUrl = "https://oauth2.googleapis.com/token";
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//
//        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
//        form.add("code", code);
//        form.add("client_id", clientId);
//        form.add("client_secret", clientSecret);
//        form.add("redirect_uri", redirectUri);
//        form.add("grant_type", "authorization_code");
//
//        HttpEntity<MultiValueMap<String, String>> tokenRequest = new HttpEntity<>(form, headers);
//
//        ResponseEntity<Map> tokenResponse = restTemplate.postForEntity(tokenUrl, tokenRequest, Map.class);
//
//        if (!tokenResponse.getStatusCode().is2xxSuccessful() || tokenResponse.getBody() == null) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to fetch token from Google");
//        }
//
//        String accessToken = (String) tokenResponse.getBody().get("access_token");
//
//        String userInfoUrl = "https://www.googleapis.com/oauth2/v3/userinfo";
//
//        HttpHeaders userInfoHeaders = new HttpHeaders();
//        userInfoHeaders.setBearerAuth(accessToken);
//
//        HttpEntity<Void> userInfoRequest = new HttpEntity<>(userInfoHeaders);
//        ResponseEntity<Map> userInfoResponse = restTemplate.exchange(
//                userInfoUrl, HttpMethod.GET, userInfoRequest, Map.class
//        );
//
//        return ResponseEntity.ok(userInfoResponse.getBody());
//    }
//}
//
