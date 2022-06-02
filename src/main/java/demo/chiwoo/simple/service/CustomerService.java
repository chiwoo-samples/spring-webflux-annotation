package demo.chiwoo.simple.service;

import demo.chiwoo.simple.entity.Customer;
import demo.chiwoo.simple.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Mono<Customer> save(Customer customer) {
        log.info("customerRepository: {}", customerRepository);
        final Customer result = customerRepository.save(customer);
        log.info("result: {}", result);
        return Mono.just(result);
    }

    public Mono<Customer> findById(Long id) {
        return Mono.just(customerRepository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    public Flux<Customer> findAll() {
        return Flux.fromIterable(customerRepository.findAll());
    }

    public Mono<Void> deleteById(Long id) {
        customerRepository.deleteById(id);
        return Mono.empty();
    }

}
