package ru.netology.geo;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;
/*
Проверить работу метода public Location byIp(String ip)
 */
class GeoServiceImplTest {
    GeoService geoService = new GeoServiceImpl();
    @Test
    void byIp() {
        assertEquals(geoService.byIp("172.").getCountry(), Country.RUSSIA);
        assertEquals(geoService.byIp("96.").getCountry(), Country.USA);
    }
}