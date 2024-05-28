package com.lucas.gatosapi.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_GATOS")
public class GatoModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Length(min = 2, message = "O nome do seu gatinho deve ter pelo menos duas letras!")
    private String nome;
    @Length(min = 1, message = "Digite uma cor válida!!")
    private String cor;
    @Length(min = 3 ,message = "Digite uma comida Válida!")
    private String comidaFavorita;

}
