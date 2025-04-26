package knims;

import java.net.*;
import java.util.Base64;
import java.io.*;

public class sms {

    private static final String API_URL = "https://api.bulksms.com/v1/messages";
    private static final String USERNAME = "aiwaaa45";  
    private static final String PASSWORD = "Izoo4321#";  

    // Dummy cache clearing method (implement if API has caching)
    public static void clearCache() {
        System.out.println("Clearing SMS cache...");
        // Logic to clear cache if needed
    }

    public static boolean sendSms(String phoneNumber, String message) {
        clearCache(); // Ensure cache is cleared before sending
        
        // Append timestamp to ensure message uniqueness
        String uniqueMessage = message + " [" + System.currentTimeMillis() + "]";

        HttpURLConnection request = null;
        try {
            URL url = new URL(API_URL);
            request = (HttpURLConnection) url.openConnection();
            request.setDoOutput(true);
            request.setRequestMethod("POST");
            request.setRequestProperty("Content-Type", "application/json");

            // Encode credentials securely
            String authStr = USERNAME + ":" + PASSWORD;
            String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
            request.setRequestProperty("Authorization", "Basic " + authEncoded);

            String jsonPayload = String.format(
                "{\"to\": \"%s\", \"encoding\": \"UNICODE\", \"body\": \"%s\"}", 
                phoneNumber, uniqueMessage
            );

            try (OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream())) {
                out.write(jsonPayload);
            }

            int responseCode = request.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            if (responseCode >= 200 && responseCode < 300) {
                // Read response
                try (BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Response: " + line);
                    }
                }
                return true;
            } else {
                // Read error response
                try (BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()))) {
                    String line;
                    while ((line = in.readLine()) != null) {
                        System.out.println("Error: " + line);
                    }
                }
                return false;
            }
        } catch (IOException ex) {
            System.out.println("An error occurred: " + ex.getMessage());
            return false;
        } finally {
            if (request != null) {
                request.disconnect();
            }
        }
    }

    public static void main(String[] args) {
        boolean result = sendSms("+254797563115", "Hey there!");
        System.out.println("SMS sent: " + result);
    }
}
