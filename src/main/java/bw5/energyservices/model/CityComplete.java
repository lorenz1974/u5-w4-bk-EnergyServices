package bw5.energyservices.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cities")
public class CityComplete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String provinceProgressive;

    @Column(nullable = false)
    private String cityProgressive;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String province;

    @Column(nullable = true)
    private String provinceCode;

    @Column(nullable = true)
    private String region;

}