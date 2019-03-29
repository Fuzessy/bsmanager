package hu.fuz.bs.finance.model;

import hu.fuz.bs.core.model.ApplicationUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor

@Entity
public class FinanceItem implements Cloneable {
  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(optional = false)
  private FinancialItemCategory category;

  @ManyToOne(optional = false)
  private Account sourceAccount;

  @ManyToOne(optional = true)
  private Account targetAccount;

  @ManyToOne(optional = false)
  private ApplicationUser recordUser;

  @Column(nullable = false) private BigDecimal amount;
  @Column(nullable = false) private Date recordTimestamp;
  @Column(nullable = false) private LocalDate transactionDate;
  @Column(nullable = false) private Integer orderNumber;
  @Column(nullable = false) private BigDecimal balance;
  @Column(length = 128) private String note;

  /**
   * Shallow copy!!!
   * @return
   */
  @Override
  public FinanceItem clone(){
    try {
      FinanceItem clone = (FinanceItem) super.clone();
      clone.id = null;
      return clone;
    } catch (CloneNotSupportedException e) {
      Assert.state(true,"Its cloneable... but I get error..");
      throw new RuntimeException(e);
    }
  }
}
