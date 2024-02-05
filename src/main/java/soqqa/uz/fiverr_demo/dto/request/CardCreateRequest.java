package soqqa.uz.fiverr_demo.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CardCreateRequest {

    private String firstName;
    private String lastName;
    private String cardNumber;
    private String cardDate;
    private String securityCode;
}
