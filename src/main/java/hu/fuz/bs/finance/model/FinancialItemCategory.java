package hu.fuz.bs.finance.model;

import lombok.*;

import javax.persistence.*;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor

@Entity
public class FinancialItemCategory {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)  private String name;
    // ez a Lombok builder miatt kell, hogy a default értéket ne írja felül
    @Builder.Default
    @Column(nullable = false)  private boolean alive = true;

}
