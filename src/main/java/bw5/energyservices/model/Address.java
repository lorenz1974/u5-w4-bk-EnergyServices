package bw5.energyservices.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue
    private long id;
    private String street;
    private String zipCode;

    @ManyToOne
    @JoinColumn(name = "city_complete_id")
    private CityComplete city;
}
