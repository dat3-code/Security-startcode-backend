package dat3.kino.repository;

import dat3.kino.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScreenRepository extends JpaRepository <Screen,Integer> {
    Optional<Screen> findByName(String name);
}
