package com.example.seguridadcap2.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table (name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ERole roleName;



}
