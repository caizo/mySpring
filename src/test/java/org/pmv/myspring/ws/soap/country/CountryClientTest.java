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
    void getCountryInfoTest() {
        TCountryInfo result = countryClient.getCountryInfo("ES");
        Assertions.assertNotNull(result);
        assertEquals("Spain", result.getSName());
        assertEquals("Madrid", result.getSCapitalCity());

        String unitedStates = countryClient.getInfo("US");
        Assertions.assertNotNull(unitedStates);
        assertEquals("United States", unitedStates);
    }

}