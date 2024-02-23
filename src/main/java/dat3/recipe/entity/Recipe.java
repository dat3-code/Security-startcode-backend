package dat3.recipe.entity;

import dat3.recipe.dto.RecipeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "recipes")
public class Recipe {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  int id;

  private String name;

  @Column(columnDefinition="TEXT")
  private String instructions;


  @Column(columnDefinition="TEXT")
  private String ingredients;

  private String thumb;

  private String youTube;
  private String source;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime edited;

  @ManyToOne
  private Category category;
  public void addCategory(Category category) {
    category.addRecipe(this);
  }

  public Recipe(RecipeDto r, Category c) {
    this.name = r.getName();
    this.instructions = r.getInstructions();
    this.ingredients = r.getIngredients();
    this.thumb = r.getThumb();
    this.youTube = r.getYouTube();
    this.source = r.getSource();
    this.category = c;
  }


}
