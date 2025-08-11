package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import ru.netology.entity.Country;

public class TestLocalizationService {
    @ParameterizedTest
    @CsvSource(value = {
                "RUSSIA",
                "USA"
        })
    public void LocalizationService_locale_test(Country cnt) {
        String ans;
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        switch (cnt) {
            case RUSSIA:
                ans = "Добро пожаловать";
                break;
            default: ans = "Welcome";
        }
        Assertions.assertEquals(ans, localizationService.locale(cnt));
    }
}

