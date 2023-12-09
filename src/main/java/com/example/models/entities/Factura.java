package com.example.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "facturas")
public @Entity class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private String observation;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "factura_id")
    private List<ItemFactura> itemFacturaList;

    public Factura() {
        this.itemFacturaList = new ArrayList<>();
    }

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }

    public Double getTotal(){
        var total = 0.0;
        var listSize = itemFacturaList.size();

        for (int i = 0; i < listSize; i++) {
            total += itemFacturaList.get(i).calcularImporte();
        }
        return total;
    }
}
