package jpa.app.department;

import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;


@Embeddable
class DepartmentPK {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}


@Entity
@Table(name = "department")
class Department {

    @EmbeddedId
    private DepartmentPK deptKey;

    private String name;
}


@Profile("test")
@Controller
@RequestMapping("dept")
@Slf4j
class DepartmentController {

    private final DepartmentRepository repo;

    DepartmentController(DepartmentRepository repo) {
        this.repo = repo;
    }

}


interface DepartmentRepository extends JpaRepository<Department, DepartmentPK> {
}

@Service
class DepartmentService {

    @Bean
    ApplicationRunner runner(DepartmentRepository repo) {
        return args -> {
        };
    }
}
