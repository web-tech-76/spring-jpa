package jpa.app.dealer;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("/dealer")
class DealerController {

    private final DealerService dealerService;

    public DealerController(DealerService dealerService) {
        this.dealerService = dealerService;
    }

    @GetMapping("/")
    List<?> findAll() {
        return this.dealerService.findAll();
    }

    @PostMapping("/new")
    Dealer save(@RequestBody Dealer dealer) {
        return this.dealerService.save(dealer);
    }

}


@Service
public class DealerService {

    private final DealerRepository dealerRepository;

    public DealerService(DealerRepository dealerRepository) {
        this.dealerRepository = dealerRepository;
    }

    List<?> findAll() {
        return this.dealerRepository.findAll();
    }

    Dealer save(Dealer dealer) {
        return this.dealerRepository.save(dealer);
    }

}

interface DealerRepository extends JpaRepository<Dealer, Long> {
}


@Entity
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
class Dealer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "dealer")
    private Collection<Vehicle> vehicles;

}


@Entity
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "dealer")
    private Dealer dealer;

}
