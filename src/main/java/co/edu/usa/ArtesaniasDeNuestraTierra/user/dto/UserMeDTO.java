package co.edu.usa.ArtesaniasDeNuestraTierra.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMeDTO {
    private int id;
    private String phone;
    private Boolean password;
    private String name;
}
