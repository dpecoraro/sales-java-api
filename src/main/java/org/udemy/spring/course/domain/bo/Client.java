package org.udemy.spring.course.domain.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name", length = 100)
    @NotEmpty(message = "{field.clientName.mandatory}")
    private String name;

    @Column(name = "CPF", length = 11)
    @NotEmpty(message = "{field.clientCPF.mandatory}")
    @CPF(message = "{field.clientCPF.shouldBeCorrect}")
    private String cpf;

    @JsonIgnore
    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private Set<OrderStore> orderSet;

    public Client(String name) {
        this.name = name;
    }

    public Client(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
