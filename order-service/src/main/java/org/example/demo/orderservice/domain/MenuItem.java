package org.example.demo.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.demo.common.Money;

import javax.persistence.*;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MenuItem {
    private Long id;
    private String name;
    @Embedded
    @AttributeOverrides(@AttributeOverride(name="amount", column=@Column(name="price")))
    private Money price;
}
