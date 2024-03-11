package dat3.kino.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    private String name;

    private Duration duration;

    private boolean is3D;

    @ManyToOne
    private Screening screening;

    public Movie(String name, Duration duration, boolean is3D) {
        this.name = name;
        this.duration = duration;
        this.is3D = is3D;
    }
}
