package dat3.recipe.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import dat3.recipe.entity.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecipeDto {
  private Integer id;
  private String name;
  private String category;
  private String instructions;
  private String ingredients;
  private String youTube;
  private String thumb;
  private String source;
  private LocalDateTime created;
  private LocalDateTime edited;

  public RecipeDto(Recipe r, boolean includeAll) {
    this.id = r.getId();
    this.name = r.getName();
    this.instructions = r.getInstructions();
    this.ingredients = r.getIngredients();
    this.youTube = r.getYouTube();
    this.thumb = r.getThumb();
    this.source = r.getSource();
    this.category = r.getCategory().getName();
    if(includeAll){
      this.created = r.getCreated();
      this.edited = r.getEdited();
    }
  }
}
