package hu.fuz.bs.finance.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long Id;

    @Column(nullable = false) private String name;

    @Column(nullable = false) private int orderNumber;

  public static boolean equalsItems(Account a, Account b){
    return (a == null && b == null)
      || (a != null && b != null && a.getId().equals(b.getId()));
  }
}
