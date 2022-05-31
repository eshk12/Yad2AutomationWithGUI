package com.Yad2AutomationWithGUI.bouncer.Utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpRequest {
    private static HttpRequest httpRequestSingleton = null;
    public static HttpRequest getInstance(){
        if(httpRequestSingleton == null){
            httpRequestSingleton = new HttpRequest();
        }
        return httpRequestSingleton;
    }

    public HttpURLConnection httpRequest(String address, String jsonInput, String requestType, String cookies) throws IOException {
            URL url = new URL(address);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setDoOutput(true);
            http.setRequestMethod(requestType);
            http.setRequestProperty("Content-Type", "application/json");
            http.setRequestProperty("Accept", "application/json");
            http.setRequestProperty("Cookie", cookies);
            if(!jsonInput.isEmpty()) {
                OutputStream os = http.getOutputStream();
                os.write(jsonInput.getBytes("UTF-8"));
                os.close();
            }
            return http;
    }

}
