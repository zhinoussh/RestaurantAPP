package com.android.shahkar.andelosapp.models;

/**
 * Created by User on 2/12/2017.
 */
public class AccessToken {

    private String access_token;
    private String token_type;
    private String userName;
    private String firstName;
    private String expires_in;

    public String getAccessToken() {
        return access_token;
    }

    public String getUserName() { return userName; }

    public String getfirstName() { return firstName; }

    public String getExpirationTime() {
        return expires_in;
    }

    public String getTokenType() {
        // OAuth requires uppercase Authorization HTTP header value for token type
        if (! Character.isUpperCase(token_type.charAt(0))) {
            token_type =
                    Character
                            .toString(token_type.charAt(0))
                            .toUpperCase() + token_type.substring(1);
        }

        return token_type;
    }
}
