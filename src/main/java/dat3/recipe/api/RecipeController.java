package dat3.recipe.api;

import dat3.recipe.dto.RecipeDto;
import dat3.recipe.service.RecipeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

 private RecipeService recipeService;

  public RecipeController(RecipeService recipeService) {
    this.recipeService = recipeService;
  }

  @GetMapping
  public List<RecipeDto> getAllRecipes(@RequestParam(required = false) String category) {
    if(category != null) {
      System.out.println("Category: " + category);
    }
    return recipeService.getAllRecipes(category);
  }

  @GetMapping(path ="/{id}")
  public RecipeDto getRecipeById(@PathVariable int id) {
    return recipeService.getRecipeById(id);
  }

  @PostMapping
  public RecipeDto addRecipe(@RequestBody RecipeDto request) {
    return recipeService.addRecipe(request);
  }

  @PutMapping(path = "/{id}")
  public RecipeDto addRecipe(@RequestBody RecipeDto request,@PathVariable int id) {
    return recipeService.editRecipe(request,id);
  }

  @DeleteMapping(path = "/{id}")
  public ResponseEntity deleteRecipe(@PathVariable int id) {
    return recipeService.deleteRecipe(id);
  }

}
