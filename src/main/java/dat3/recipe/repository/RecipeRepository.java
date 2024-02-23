package dat3.recipe.repository;

import dat3.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository  extends JpaRepository<Recipe,Integer> {
  List<Recipe> findByCategoryName(String categoryName);
}