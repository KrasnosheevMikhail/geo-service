package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;


import static ru.netology.entity.Country.RUSSIA;

public class TestGeoService {
    @ParameterizedTest
    @CsvSource(value = {
            "172.0.32.11",
            "96.44.183.149",
            "127.0.0.1",
            "172.0.0.0"})
    public void GeoService_byIp_test(String ip) {
        Location expAnsw = null;
        GeoServiceImpl geoService = new GeoServiceImpl();
        if ("127.0.0.1".equals(ip)) {
            expAnsw = new Location(null, null, null, 0);
        } else if ("172.0.32.11".equals(ip)) {
            expAnsw = new Location("Moscow", RUSSIA, "Lenina", 15);
        } else if ("96.44.183.149".equals(ip)) {
            expAnsw = new Location("New York", Country.USA, " 10th Avenue", 32);
        } else if (ip.startsWith("172.")) {
            expAnsw = new Location("Moscow", RUSSIA, null, 0);
        } else if (ip.startsWith("96.")) {
            expAnsw = new Location("New York", Country.USA, null, 0);
        }
        Assertions.assertEquals(expAnsw, geoService.byIp(ip));
    }
}

