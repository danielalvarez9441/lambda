package lambdafunction.modelo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;



@Getter
@Setter
public class CountryInfo {

    private Name name;
    private Map<String, Currency> currencies;
    private String[] capital;
    private long population;
    private String[] continents;

    @Getter
    @Setter
    public static class Name {
        private String common;
        private String official;
        private Map<String, LanguageNames> nativeName;
    }

    @Getter
    @Setter
    public static class LanguageNames {
        private String common;
        private String official;
    }

    @Getter
    @Setter
    public static class Currency {
        private String name;
        private String symbol;
    }
}