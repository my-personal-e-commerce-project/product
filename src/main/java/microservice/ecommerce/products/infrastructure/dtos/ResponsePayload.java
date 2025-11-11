package microservice.ecommerce.products.infrastructure.dtos;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ResponsePayload {

    @Builder.Default
    public String message = "success";
    public Object data;
    public Map<String, String> errors;
}
