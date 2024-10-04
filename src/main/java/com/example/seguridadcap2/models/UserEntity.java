package com.example.seguridadcap2.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(max=80)
    private String userName;

    @NotBlank
    private String password;



    @ManyToMany(fetch = FetchType.EAGER , targetEntity = RoleEntity.class , cascade = CascadeType.PERSIST)
    @JoinTable( name = "user_roles"                         ,
                joinColumns = @JoinColumn(name = "user_id") ,
                inverseJoinColumns = @JoinColumn(name="role_id")
    )

    private Set<RoleEntity> roles;

}
