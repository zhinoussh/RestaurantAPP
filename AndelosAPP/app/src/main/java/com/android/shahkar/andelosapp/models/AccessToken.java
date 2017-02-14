package com.android.shahkar.andelosapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by User on 2/12/2017.
 */
public class AccessToken implements Parcelable {

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.access_token);
        dest.writeString(this.token_type);
        dest.writeString(this.userName);
        dest.writeString(this.firstName);
        dest.writeString(this.expires_in);
    }

    public AccessToken() {
    }

    protected AccessToken(Parcel in) {
        this.access_token = in.readString();
        this.token_type = in.readString();
        this.userName = in.readString();
        this.firstName = in.readString();
        this.expires_in = in.readString();
    }

    public static final Parcelable.Creator<AccessToken> CREATOR = new Parcelable.Creator<AccessToken>() {
        @Override
        public AccessToken createFromParcel(Parcel source) {
            return new AccessToken(source);
        }

        @Override
        public AccessToken[] newArray(int size) {
            return new AccessToken[size];
        }
    };
}
