package dat3.kino.configuration;

import dat3.kino.entity.*;
import dat3.kino.repository.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class InitDataReservation implements ApplicationRunner {
    private final MovieRepository movieRepository;
    private final CinemaRepository cinemaRepository;
    private final ScreenRepository screenRepository;
    private final ScreeningRepository screeningRepository;

    public InitDataReservation(MovieRepository movieRepository,
                               CinemaRepository cinemaRepository,
                               ScreenRepository screenRepository,
                               ScreeningRepository screeningRepository) {
        this.movieRepository = movieRepository;
        this.cinemaRepository = cinemaRepository;
        this.screenRepository = screenRepository;
        this.screeningRepository = screeningRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        initMovies();
        initScreenings();
    }

    public void initMovies() {
        Duration durationAvatar = Duration.ofHours(2).plusMinutes(30);
        Movie movie1 = new Movie("Avatar", durationAvatar, true);
        movieRepository.save(movie1);

        Duration durationTheSeventhSeal = Duration.ofHours(3).plusMinutes(30);
        Movie movie2 = new Movie("The Seventh Seal", durationTheSeventhSeal, false);
        movieRepository.save(movie2);

        Duration durationDudeWheresMyCar = Duration.ofHours(1).plusMinutes(30);
        Movie movie3 = new Movie("Dude, Where's My Car?", durationDudeWheresMyCar, false);
        movieRepository.save(movie3);
    }

    public void initScreenings() {
        Movie movie1 = movieRepository.findByName("Avatar").orElseThrow();
        Movie movie2 = movieRepository.findByName("The Seventh Seal").orElseThrow();
        Movie movie3 = movieRepository.findByName("Dude, Where's My Car?").orElseThrow();

        Cinema cinemaCopenhagen = cinemaRepository.findByName("Cinema Copenhagen").orElseThrow();
        Cinema cinemaRoskilde = cinemaRepository.findByName("Cinema Roskilde").orElseThrow();
        Screen screen1 = screenRepository.findByName("Screen 1").orElseThrow();
        Screen screen2 = screenRepository.findByName("Screen 2").orElseThrow();

        LocalDateTime now = LocalDateTime.now();

        Screening screening1 = new Screening(now, movie1, cinemaCopenhagen, screen1);
        Screening screening2 = new Screening(now.plusDays(1), movie2, cinemaCopenhagen, screen1);
        Screening screening3 = new Screening(now.plusDays(2), movie3, cinemaRoskilde, screen2);

        screeningRepository.saveAll(List.of(screening1, screening2, screening3));
    }
}