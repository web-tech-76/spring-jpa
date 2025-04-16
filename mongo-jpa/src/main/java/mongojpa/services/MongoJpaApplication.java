package mongojpa.services;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@SpringBootApplication
public class MongoJpaApplication {


    public static void main(String[] args) {
        SpringApplication.run(MongoJpaApplication.class, args);

    }


}

@RestController
@RequestMapping("/products")
class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping("/new")
    Mono<Product> createNew(@RequestBody Product product) {
        return this.productRepository.save(product);
    }

    @GetMapping("/all")
    Flux<Product> findAll() {
        return this.productRepository.findAll();
    }

    @GetMapping("/{id}")
    Mono<Product> findById(@PathVariable String id) {
        return this.productRepository.findById(id);
    }

}


interface ProductRepository extends ReactiveMongoRepository<Product, String> {
}

@Service
class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    Mono<Product> createNew(Product product) {
        System.out.println("received data  -> " + product.toString());
        return this.productRepository.save(product);
    }

    Mono<Product> findByProductId(Mono<String> productId) {
        return this.productRepository.findById(productId);
    }

    Flux<Product> findAllProducts() {
        return this.productRepository.findAll();
    }
}


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
class Product {

    private String _id;

    private String productId;
    private String name;
    private String description;

    public Product(String productId, String name, String description) {
        this.productId = productId;
        this.name = name;
        this.description = description;
    }
}

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductDetails {

    private String _id;
    private String productDetailId;
    private String category;
    private Double price;
    private String seller;
}

