package com.example.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@Table(name = "clientes")
public @Entity class Cliente implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty
    private String nombre;

    @NotEmpty
    private String apellido;

    @NotEmpty
    @Email
    private String email;

    @NotNull
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date createdAt;

    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade =CascadeType.ALL)
    private List<Factura> facturaList;

    private String foto;

    public Cliente(){
        facturaList = new ArrayList<>();
    }

    /* @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    } */

    public void addFactura(Factura factura){
        this.facturaList.add(factura);
    }
}
