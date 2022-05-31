package com.Yad2AutomationWithGUI.bouncer.Services;

import com.Yad2AutomationWithGUI.bouncer.Models.Ad;
import com.Yad2AutomationWithGUI.bouncer.Models.Token;
import com.Yad2AutomationWithGUI.bouncer.Utils.HttpRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

public class Services {
    private static Services servicesSingleton = null;

    public static Services getInstance() {
        if (servicesSingleton == null) {
            servicesSingleton = new Services();
        }
        return servicesSingleton;
    }

    public Token getAuthorized(String email, String password) throws IOException {

        HttpURLConnection request = HttpRequest.getInstance().httpRequest(
                "https://gw.yad2.co.il/auth/login",
                "{\"email\": \"" + email + "\", \"password\": \"" + password + "\"}",
                "POST",
                ""
        );
        BufferedReader br;
        StringBuilder response = new StringBuilder();
        if (request.getResponseCode() == 200) {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                response.append(strCurrentLine);
            }
        } else {
            br = new BufferedReader(new InputStreamReader(request.getErrorStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                response.append(strCurrentLine);
            }
        }
        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(String
                .valueOf(response)).getAsJsonObject();
        Token token = null;
        if (jo.get("message").getAsString().equals("OK")) {
            token = new Token(
                    jo.get("data").getAsJsonObject().get("access_token").getAsString(),
                    jo.get("data").getAsJsonObject().get("refresh_token").getAsString());
        }
        return token;
    }

    public ArrayList getAds(String cookies) throws IOException {
        HttpURLConnection request = HttpRequest.getInstance().httpRequest(
                "https://gw.yad2.co.il/personal-ads/",
                "",
                "GET",
                cookies
        );
        BufferedReader br;
        StringBuilder response = new StringBuilder();
        if (request.getResponseCode() == 200) {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                response.append(strCurrentLine);
            }
        }
        ArrayList<Ad> adsArrayList = null;

        JsonParser parser = new JsonParser();
        JsonObject jo = parser.parse(String
                .valueOf(response)).getAsJsonObject();
        if (!jo.isJsonNull()) {
            JsonArray adsArray = jo.get("data").getAsJsonArray();
            adsArrayList = new ArrayList<Ad>();
            for (int i = 0; i < adsArray.size(); i++) {
                Ad currentAd = new Ad(
                        adsArray.get(i).getAsJsonObject().get("order_id").getAsInt(),
                        adsArray.get(i).getAsJsonObject().get("meta_data").getAsJsonObject().get("title").getAsString(),
                        adsArray.get(i).getAsJsonObject().get("dates").getAsJsonObject().get("reBounce").getAsString()
                );
                adsArrayList.add(currentAd);
            }
        }
        return adsArrayList;
    }

    public boolean bounceAds(int adId, String cookies) throws IOException {
        HttpURLConnection request = HttpRequest.getInstance().httpRequest(
                "https://gw.yad2.co.il/personal-ads/manual-bounce/"+adId,
                "",
                "PUT",
                cookies
        );
        BufferedReader br = null;
        StringBuilder response = new StringBuilder();

        JsonParser parser = new JsonParser();
        if (request.getResponseCode() == 200) {
            br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                response.append(strCurrentLine);
            }
            System.out.println(String.format("Ad id: %s - has been bounced successfully.", adId));
        } else {
            br = new BufferedReader(new InputStreamReader(request.getErrorStream()));
            String strCurrentLine;
            while ((strCurrentLine = br.readLine()) != null) {
                response.append(strCurrentLine);
            }
            JsonObject jo = parser.parse(String.valueOf(response)).getAsJsonObject();
            System.out.println(String.format("Ad id: %s - retreived message: \"%s\"", adId ,jo.get("message").getAsString()));
        }
        return false;
    }
}
