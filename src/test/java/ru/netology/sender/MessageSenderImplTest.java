package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {
    MessageSenderImpl messageSenderImpl;
    GeoServiceImpl geoServiceImpl;
    LocalizationServiceImpl localizationServiceImpl;
    @BeforeEach
    void init() {
        messageSenderImpl = Mockito.mock(MessageSenderImpl.class);
        geoServiceImpl = Mockito.mock(GeoServiceImpl.class);
        localizationServiceImpl = Mockito.mock(LocalizationServiceImpl.class);
    }

    @Test
    void testSendRu() {
        Map<String, String> headers = new HashMap<>();
        Location location = new Location("Н", Country.RUSSIA, null, 0);
        String ip = "172.";

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Mockito.when(geoServiceImpl.byIp(ip)).thenReturn(location);
        Mockito.when(localizationServiceImpl.locale(location.getCountry())).thenReturn("Добро пожаловать");

        Assertions.assertEquals("Добро пожаловать", messageSenderImpl.send(headers));
    }

    @Test
    void testSendEng() {
        Map<String, String> headers = new HashMap<>();
        Location location = new Location("N", Country.USA, null, 0);
        String ip = "96.";

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        Mockito.when(geoServiceImpl.byIp(ip)).thenReturn(location);
        Mockito.when(localizationServiceImpl.locale(location.getCountry())).thenReturn("Welcome");

        Assertions.assertEquals("Welcome", messageSenderImpl.send(headers));
    }
}