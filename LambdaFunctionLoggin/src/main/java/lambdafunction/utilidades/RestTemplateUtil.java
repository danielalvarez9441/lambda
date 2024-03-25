package lambdafunction.utilidades;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

public class RestTemplateUtil {
    public static <T> ResponseEntity<List<T>> restTemplatePlantilla(String uri, HttpMethod method, HttpEntity<?> requestEntity, Class<T[]> responseType){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T[]> responseEntity = restTemplate.exchange(uri, method, requestEntity, responseType);
        List<T> resultList = Arrays.asList(responseEntity.getBody());

        return new ResponseEntity<>(resultList, responseEntity.getStatusCode());
    }
}