package org.pmv.myspring.ws.soap.country;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;


@Service
@RequiredArgsConstructor
public class CountryClient {

    private final CountryInfoService countryInfoService = new CountryInfoService();

    private final WebServiceTemplate webServiceTemplate;

    public TCountryInfo getCountryInfo(String countryISOCode) {
        CountryInfoServiceSoapType port = countryInfoService.getCountryInfoServiceSoap();
        return port.fullCountryInfo(countryISOCode);
    }

    public String getInfo(String countryName) {
        return countryInfoService.getCountryInfoServiceSoap().countryName(countryName);
    }


}