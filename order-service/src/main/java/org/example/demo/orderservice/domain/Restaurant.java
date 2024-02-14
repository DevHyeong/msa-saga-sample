package org.example.demo.orderservice.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Entity
@Getter
@NoArgsConstructor
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    @ElementCollection
    @CollectionTable(name = "restaurant_menu_items")
    private List<MenuItem> menuItems = new ArrayList<>();
    private String name;

    public Restaurant(String name, List<MenuItem> menuItems){
        this.name = name;
        this.menuItems = menuItems;
    }

    public Optional<MenuItem> findMenuItem(Long menuItemId){
        return menuItems.stream().filter(mi -> mi.getId().equals(menuItemId)).findFirst();
    }

}
