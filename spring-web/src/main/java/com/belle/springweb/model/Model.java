package com.belle.springweb.model;

import jakarta.persistence.*;
import lombok.*;


@Data
@Table(name = "models")
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

}
