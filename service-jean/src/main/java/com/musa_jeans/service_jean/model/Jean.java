package com.musa_jeans.service_jean.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "jean")
@Entity
@Schema(description = "Modelo que representa un Jean en el sistema")
public class Jean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int precio;
    
    private String color;

    @Schema(description = "Talla en formato de letras", example = "XL")
    private String talla;
    
    @Schema(description = "Tiro en formato string", example = "Corto")
    private String tiro;
    private String descripcion;
    
    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

}
