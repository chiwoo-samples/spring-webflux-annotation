package demo.chiwoo.simple.api;

import demo.chiwoo.simple.entity.Customer;
import demo.chiwoo.simple.repository.CustomerRepository;
import demo.chiwoo.simple.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

// @ContextConfiguration(classes = {CustomerRepository.class, CustomerService.class,})
@WebFluxTest(CustomerController.class)
@ExtendWith(SpringExtension.class)
@Import({CustomerRepository.class, CustomerService.class,})
class CustomerControllerTests {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WebTestClient client;

    @MockBean
    CustomerRepository repository;

    /**
     * @see CustomerController#save(Customer)
     */
    @Test
    public void test_add_customer() {
        final Customer customer = new Customer(System.currentTimeMillis(), "myname", "C1001", 45, "M");
        given(repository.save(any())).willReturn(customer);
        // Mockito.when(repository.save(customer)).thenReturn( Mono.just(customer));
        //Customer result =
        client.post().uri(CustomerController.BASE_URI)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customer))
                .exchange()
                .expectStatus().isCreated()
                .expectBody() //.jsonPath("name", "myname")
                .jsonPath("$.name").isEqualTo(customer.getName())
                .jsonPath("$.age").isEqualTo(customer.getAge())
                .jsonPath("$.gender").isEqualTo(customer.getGender())
        // .returnResult()
        // .getResponseBody()
        // .returnResult(Customer.class)
        // .getResponseBody().blockFirst()
        // .bodyToMono(Customer.class)
        // .block()
        ;
        // log.info("result: {}", result);
        // id = customer.getId();
        // assertThat(customer.getName(), is(newCustomer.getName()));
    }


}
