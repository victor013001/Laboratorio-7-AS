package com.udea.persona.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Persona implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idperson")
    private Long idPerson;

    @Column(name = "firstname", nullable = false, length = 80)
    private @NonNull String firstName;

    @Column(name = "lastname", nullable = false, length = 80)
    private @NonNull String lastName;

    @Column(name = "email", nullable = false, length = 80)
    private @NonNull String email;
}