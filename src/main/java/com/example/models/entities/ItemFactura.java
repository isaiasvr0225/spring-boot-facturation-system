package com.example.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Getter
@Setter
@Table(name = "items_facturas")
public @Entity class ItemFactura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidad;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    public Double calcularImporte(){
        return this.cantidad.doubleValue() * this.producto.getPrecio();
    }
}
