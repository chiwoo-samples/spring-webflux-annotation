package demo.chiwoo.simple.api;


import demo.chiwoo.simple.entity.Customer;
import demo.chiwoo.simple.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(CustomerController.BASE_URI)
public class CustomerController {

    static final String BASE_URI = "/api/customers";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Customer> save(@RequestBody Customer customer) {
        log.info("customer: {}", customer);
        return customerService.save(customer);
    }

    @GetMapping("/{id}")
    public Mono<Customer> findById(@PathVariable("id") Long id) {
        return customerService.findById(id);
    }

    @GetMapping
    public Flux<Customer> findAll() {
        return customerService.findAll();
    }

    @DeleteMapping("{id}")
    public Mono<Void> deleteById(@PathVariable("id") Long id) {
        return customerService.deleteById(id);
    }
}
