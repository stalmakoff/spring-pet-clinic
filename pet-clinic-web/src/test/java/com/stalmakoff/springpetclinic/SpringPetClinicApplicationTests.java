package com.stalmakoff.springpetclinic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
//conf spring context execute
@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringPetClinicApplicationTests {

    // to bring heavy spring context its longer in integration tests
    @Test
    void contextLoads() {
    }

}
