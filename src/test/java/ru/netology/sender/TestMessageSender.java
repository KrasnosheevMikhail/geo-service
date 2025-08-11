package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.HashMap;
import java.util.Map;

import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import ru.netology.geo.GeoServiceImpl;

import ru.netology.i18n.LocalizationServiceImpl;

import static ru.netology.entity.Country.RUSSIA;

public class TestMessageSender {

    @ParameterizedTest
    @CsvSource(value = {"172.0.32.11, Добро пожаловать", "96.44.183.149, Welcome" })

    public void test_message_sender_mockito(String ip, String greeting){

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(ip.startsWith("172")? new Location("Moscow", RUSSIA, "Lenina", 15)
                        : new Location("New York", Country.USA, " 10th Avenue", 32));


        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when((localizationService.locale(geoService.byIp(ip).getCountry())))
                .thenReturn(ip.startsWith("172")?"Добро пожаловать":"Welcome");

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String actual = messageSender.send(headers);
        Assertions.assertEquals(actual, greeting);


    }

}
