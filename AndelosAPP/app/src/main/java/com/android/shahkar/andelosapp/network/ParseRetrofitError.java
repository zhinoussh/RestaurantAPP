package com.android.shahkar.andelosapp.network;

import org.json.JSONObject;

public class ParseRetrofitError {

    public static String getError(String message) {
        String error_message = "Error";

        try {
            JSONObject jsonObject = new JSONObject(message);

            if (jsonObject.has("error_description"))
                error_message = jsonObject.getString("error_description");
            else if (jsonObject.has("Message"))
                error_message = jsonObject.getString("Message");
        } catch (Exception ex) {
            error_message = ex.toString();
        }
        return error_message;
    }
}
