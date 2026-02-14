import com.google.gson.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ApiQuestionLoader {

    public static List<QuestionModel> loadFromApi() {

        try {
            String url = "https://opentdb.com/api.php?amount=10&category=18&type=multiple";

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            JsonObject root = JsonParser.parseString(json).getAsJsonObject();
            JsonArray results = root.getAsJsonArray("results");

            List<QuestionModel> questions = new ArrayList<>();

            for (JsonElement element : results) {

                JsonObject obj = element.getAsJsonObject();

                String question = obj.get("question").getAsString();
                String correctAnswer = obj.get("correct_answer").getAsString();

                JsonArray incorrect = obj.getAsJsonArray("incorrect_answers");

                List<String> options = new ArrayList<>();

                options.add(correctAnswer);

                for (JsonElement inc : incorrect) {
                    options.add(inc.getAsString());
                }

                java.util.Collections.shuffle(options);

                int correctIndex = options.indexOf(correctAnswer);

                QuestionModel q = new QuestionModel(
                        question,
                        options.toArray(new String[0]),
                        correctIndex
                );

                questions.add(q);
            }

            return questions;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}