package org.example.demo.orderservice.domain;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
public class Restaurant {
    @Id
    private Long id;

    @Embedded
    @ElementCollection
    @CollectionTable(name = "restaurant_menu_items")
    private List<MenuItem> menuItems;
    private String name;

    public Optional<MenuItem> findMenuItem(String menuItemId){
        return menuItems.stream().filter(mi -> mi.getId().equals(menuItemId)).findFirst();
    }

}
