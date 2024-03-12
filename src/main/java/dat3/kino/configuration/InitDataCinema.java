package dat3.kino.configuration;

import dat3.kino.entity.Cinema;
import dat3.kino.entity.Screen;
import dat3.kino.entity.Seat;
import dat3.kino.repository.CinemaRepository;
import dat3.kino.repository.ScreenRepository;
import dat3.kino.repository.SeatRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InitDataCinema implements ApplicationRunner {

    private final SeatRepository seatRepository;
    private final ScreenRepository screenRepository;
    private final CinemaRepository cinemaRepository;

    public InitDataCinema(SeatRepository seatRepository, ScreenRepository screenRepository, CinemaRepository cinemaRepository) {
        this.seatRepository = seatRepository;
        this.screenRepository = screenRepository;
        this.cinemaRepository = cinemaRepository;
    }

    @Override
    public void run(ApplicationArguments args) {
        initScreens();
        initCinemas();
        initSeats();

    }

    public void initScreens() {
        List<Screen> screens = List.of(
                new Screen("Screen 1", 240, 20),
                new Screen("Screen 2", 240, 20),
                new Screen("Screen 3", 320, 22),
                new Screen("Screen 4", 320, 22),
                new Screen("Screen 5", 400, 25),
                new Screen("Screen 6", 400, 25)
        );

        screenRepository.saveAll(screens);
    }

    public void initCinemas() {
        List<Screen> screens = screenRepository.findAll();

        Cinema cinema1 = new Cinema("Cinema Copenhagen", "Copenhagen");
        cinema1.setScreens(List.of(screens.get(0), screens.get(1)));

        Cinema cinema2 = new Cinema("Cinema Roskilde", "Roskilde");
        cinema2.setScreens(List.of(screens.get(2), screens.get(3), screens.get(4), screens.get(5)));

        cinemaRepository.saveAll(List.of(cinema1, cinema2));
    }

    public void initSeats() {
        List<Screen> screens = screenRepository.findAll();

        for (Screen screen : screens) {
            int capacity = screen.getCapacity();

            for (int i = 1; i <= capacity; i++) {
                Seat seat = new Seat();
                seat.setScreen(screen);
                seat.setId((screen.getId() - 1) * capacity + i); // Calculate a unique seat ID
                seatRepository.save(seat);

                screen.getSeats().add(seat);
            }

            screenRepository.save(screen);
        }
    }
}
