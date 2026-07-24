package com.biblioteca.biblioteca.model;


import jakarta.persistence.*;
import lombok.*;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RolesEntity implements GrantedAuthority {

    @Id
    private Long id;

    private String nome;

    @Override
    public @Nullable String getAuthority() {
        return nome;
    }
}


