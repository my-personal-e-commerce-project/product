package microservice.ecommerce.products.infrastructure.dtos;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ResponsePayload {

    public String message;
    public Object data;
    public Map<String, String> errors;
}
