package dat3.recipe.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/info")
public class InfoController {

  @GetMapping
  public String getInfo() {
    return " {\n" +
            "  \"reference\": \"https://www.themealdb.com/\",\n" +
            "  \"created\": \"2023-12-18T15:22:55.724Z\",\n" +
            "  \"info\": \"Data are created via the free recipe API from the mealDB, and translated to danish via the Microsoft Translator API.\"\n" +
            "}";
  }
}
