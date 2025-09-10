import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class RESTfulApiHM1 {

    public static void main(String[] args) throws Exception {
        String POSTapiUrl = "https://student-info-api.netlify.app/.netlify/functions/submit_student_info";
        String jsonInputString = "{ \"UCID\": \"aoy\", " +
                "\"first_name\": \"Anthony\",  " +
                "\"last_name\": \"Yagoda\", " +
                "\"github_username\": \"AnthonyYagoda\", " +
                "\"discord_username\": \"anthonyy8754\", " +
                "\"favorite_cartoon\": \"Adventure Time\", " +
                "\"favorite_language\": \"Spanish\", " +
                "\"movie_or_game_or_book\": \"LEGO STAR WARS Complete Edition PS3\", " +
                "\"section\": \"103\" }";

        URL url = new URL(POSTapiUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // GET API URL to test if JSON data went through
        String GETapiUrl = "https://student-info-api.netlify.app/.netlify/functions/submit_student_info?UCID=aoy&section=103";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GETapiUrl))
                .GET() // Specify the HTTP GET method
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Print the response status code
            System.out.println("Status Code: " + response.statusCode());

            // Print the response body
            System.out.println("Response Body: " + response.body());

        } catch (Exception e) {
            // Catch any errors in the API GET request
            e.printStackTrace();
        }

    }
}