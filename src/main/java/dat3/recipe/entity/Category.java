package dat3.recipe.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @Column(unique = true)
  private String name;

  @CreationTimestamp
  private LocalDateTime created;

  @UpdateTimestamp
  private LocalDateTime edited;

  @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
  private Set<Recipe> recipes;

  public void  addRecipe(Recipe recipe) {
    this.recipes.add(recipe);
    recipe.setCategory(this);
  }

  public Category(String name) {
    this.name = name;
  }

}
