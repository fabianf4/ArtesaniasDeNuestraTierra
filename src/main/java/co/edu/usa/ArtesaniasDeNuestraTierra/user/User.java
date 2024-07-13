package co.edu.usa.ArtesaniasDeNuestraTierra.user;

import io.micrometer.common.lang.Nullable;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    private int id;

    @Column(unique = true)
    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String name;
}
