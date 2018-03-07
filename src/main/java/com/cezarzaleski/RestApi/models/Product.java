package com.cezarzaleski.RestApi.models;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Campo nome obrigatório")
    @NotBlank(message = "Campo nome obrigatório")
    @Size(min = 4, max = 255)
    private String name;

    @Min(value = 0)
    @Max(value = 1000)
    private Integer qtd;

    private Date dateCreated;

    public Product() {}

    public Product(String name, Integer qtd) {
        this.name = name;
        this.qtd = qtd;
    }

    @PrePersist
    public void onPrePersist() {
        if(this.dateCreated == null) {
            this.dateCreated = new Date();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQtd() {
        return qtd;
    }

    public void setQtd(Integer qtd) {
        this.qtd = qtd;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", qtd=" + qtd +
                ", dateCreated=" + dateCreated +
                '}';
    }
}
