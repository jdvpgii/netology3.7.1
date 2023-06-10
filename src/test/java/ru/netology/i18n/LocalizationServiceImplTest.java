package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {
    LocalizationService localizationService = new LocalizationServiceImpl();
    @Test
    void localeRu() {
        Assertions.assertEquals(localizationService.locale(Country.RUSSIA), "Добро пожаловать");
    }

    @Test
    void localeUSA() {
        Assertions.assertEquals(localizationService.locale(Country.USA), "Welcome");
    }
}