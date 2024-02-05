package soqqa.uz.fiverr_demo.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CardResponse {

    private String firstName;
    private String lastName;
    private String cardNumber;
    private String cardDate;
    private String securityCode;
}