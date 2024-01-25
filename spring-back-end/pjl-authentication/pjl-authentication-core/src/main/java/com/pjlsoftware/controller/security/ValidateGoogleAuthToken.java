package com.pjlsoftware.controller.security;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.pjlsoftware.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;

@Component
public class ValidateGoogleAuthToken {
    private static final Logger LOG = LoggerFactory.getLogger(ValidateGoogleAuthToken.class);
    private static final String CLIENT_ID = "603021351518-d3kr8eie71dcfei6ud9531kmp8bbhqq3.apps.googleusercontent.com";

    private static final NetHttpTransport transport = new NetHttpTransport();
    private static final JsonFactory jsonFactory = new GsonFactory();

    public static Optional<User> verifyGoogleAuthToken(String idTokenString) {
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                // Specify the CLIENT_ID of the app that accesses the backend:
                .setAudience(Collections.singletonList(CLIENT_ID))
                // Or, if multiple clients access the backend:
                //.setAudience(Arrays.asList(CLIENT_ID_1, CLIENT_ID_2, CLIENT_ID_3))
                .build();

        // (Receive idTokenString by HTTPS POST)
        try {
            GoogleIdToken idToken = verifier.verify(idTokenString);

            if (idToken != null) {
                Payload payload = idToken.getPayload();

                // Print user identifier
                String userId = payload.getSubject();

                // Get profile information from payload
                String email = payload.getEmail();
                boolean emailVerified = payload.getEmailVerified();
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String locale = (String) payload.get("locale");
                String familyName = (String) payload.get("family_name");
                String givenName = (String) payload.get("given_name");

                // Use or store profile information
                // ...
                User user = new User(
                        givenName.substring(0, Math.min(givenName.length(), 25)),
                        familyName.substring(0, Math.min(familyName.length(), 30)),
                        email.substring(0, Math.min(email.length(), 40))
                );
                return Optional.of(user);
            } else {
                LOG.info("Invalid ID token.");
            }
        } catch (Exception e) {
            LOG.info("Exception in verifyGoogleAuthToken: ", e);
        }
        return Optional.empty();
    }
}
