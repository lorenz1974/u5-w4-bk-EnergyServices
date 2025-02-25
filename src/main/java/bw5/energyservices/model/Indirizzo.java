package bw5.energyservices.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "indirizzo")
public class Indirizzo {
    @Id
    @GeneratedValue
    private long id;
    private String via;
    private int civico;
    private int cap;
    private String localita;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id")
    private Province province;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comune_code")
    private City city;

}
