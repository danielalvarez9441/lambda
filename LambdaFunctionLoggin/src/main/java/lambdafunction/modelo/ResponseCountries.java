package lambdafunction.modelo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import software.amazon.awssdk.services.secretsmanager.endpoints.internal.Value;

import java.util.List;

@Getter
@Setter
public class ResponseCountries extends ResponseGeneral{
    private Object countryInfos;
    public ResponseCountries(String mensaje, List<CountryInfo> countryInfoList) {
        super(mensaje);
        this.countryInfos = countryInfoList;
    }
}
