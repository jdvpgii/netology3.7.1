package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {
    MessageSender messageSender;
    LocalizationService localizationService;
    GeoService geoService;


    @BeforeEach
    void init() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
        localizationService = Mockito.mock(LocalizationService.class);
        geoService = Mockito.mock(GeoService.class);
    }


    @Test
    void testSendRu() {
        Map<String, String> headers = new HashMap<>();
        Location location = new Location("Н", Country.RUSSIA, null, 0);
        String ip = "172.";

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Mockito.when(geoService.byIp(ip)).thenReturn(location);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn("Добро пожаловать");

        Assertions.assertEquals("Добро пожаловать", messageSender.send(headers));
    }

    @Test
    void testSendEng() {
        Map<String, String> headers = new HashMap<>();
        Location location = new Location("N", Country.USA, null, 0);
        String ip = "96.";

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Mockito.when(geoService.byIp(ip)).thenReturn(location);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn("Welcome");

        Assertions.assertEquals("Welcome", messageSender.send(headers));
    }
}