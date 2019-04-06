package hu.fuz.bs.shopping.model;

import lombok.*;

import javax.persistence.*;


@Data @Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class ItemCategory {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 64, nullable = false)
  private String name;

  @Builder.Default
  @Column(nullable = false)
  private boolean alive = true;

}
