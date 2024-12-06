package org.pmv.myspring.ws.soap.country;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;


@Service
@RequiredArgsConstructor
public class CountryClient {


    private final WebServiceTemplate webServiceTemplate;

    public TCountryInfo getCountryInfo(String countryISOCode) {
        CountryInfoService service = new CountryInfoService();
        CountryInfoServiceSoapType port = service.getCountryInfoServiceSoap();
        return port.fullCountryInfo(countryISOCode);
    }
}