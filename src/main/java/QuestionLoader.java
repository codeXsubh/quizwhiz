import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;

public class QuestionLoader {

    public static List<QuestionModel> loadQuestions() {

        try {
            var stream = QuestionLoader.class
                    .getClassLoader()
                    .getResourceAsStream("questions.json");

            if (stream == null) {
                throw new RuntimeException("questions.json NOT FOUND in resources");
            }

            InputStreamReader reader = new InputStreamReader(stream);

            Gson gson = new Gson();
            Type listType = new TypeToken<List<QuestionModel>>(){}.getType();

            return gson.fromJson(reader, listType);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load questions");
        }
    }
}