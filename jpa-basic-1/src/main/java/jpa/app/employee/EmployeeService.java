package jpa.app.employee;


import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/emp")
class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    List<?> findAll() {
        return this.employeeService.findAll();
    }

    @PostMapping("/new")
    Employee createNew(@RequestBody Employee employee) {
        return this.employeeService.createNew(employee);
    }
}


@Service
class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    List<?> findAll() {
        return this.employeeRepository.findAll();
    }

    Employee createNew(Employee employee) {
        return this.employeeRepository.save(employee);
    }

}


interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@SecondaryTable(name = "employee_info", pkJoinColumns = @PrimaryKeyJoinColumn(name = "id"))
class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToOne(cascade = {CascadeType.PERSIST})
    private Job job;

    @Column(table = "employee_info")
    private String address;

}

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

}
