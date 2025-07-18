package org.koreait.member.social.test;

import org.junit.jupiter.api.Test;
import org.koreait.member.social.entities.AuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Map;


@SpringBootTest
public class AccessTokenTest {
    private static final Logger log = LoggerFactory.getLogger(AccessTokenTest.class);
    @Autowired
    private RestTemplate restTemplate;

    @Test
    void test() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "authorization_code");
        body.add("client_id", "a6751787adeb98a5a34c22ffa2e584e5");
        body.add("redirect_uri", "http://localhost:3000/member/social/callback/kakao");
        body.add("code", "6Klc1BTy5ZOabAZ-LuV8KAIrh2sogDrH1yDkD5-rvnKyXINLEPdqWwAAAAQKDRlTAAABmButVcnMISgqRbFCUQ");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(body, headers);


        String requestUrl = "https://kauth.kakao.com/oauth/token";

        ResponseEntity<AuthToken> response = restTemplate.exchange(URI.create(requestUrl), HttpMethod.POST, request, AuthToken.class);

        HttpStatusCode status = response.getStatusCode();
        System.out.print("status:" + status);
        System.out.println(response.getBody());

        // access Token으로 회원 정보 조회
        AuthToken authToken = response.getBody();
        requestUrl = "https://kapi.kakao.com/v2/user/me";
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(authToken.getAccessToken());

        request = new HttpEntity<>(headers);
        ResponseEntity<Map> res = restTemplate.exchange(URI.create(requestUrl), HttpMethod.POST, request, Map.class);

        Map resBody = res.getBody();
        long id = (Long)resBody.get("id");
        System.out.println(id);
    }
}
