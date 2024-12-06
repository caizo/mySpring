package org.pmv.myspring.ws.soap.country;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CountryClientTest {

    @Autowired
    private CountryClient countryClient;

    @Test
    void getCountry() {
        TCountryInfo result = countryClient.getCountryInfo("US");
        Assertions.assertNotNull(result);
    }

}