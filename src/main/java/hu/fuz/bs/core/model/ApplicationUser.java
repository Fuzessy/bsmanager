package hu.fuz.bs.core.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @Builder @AllArgsConstructor
public class ApplicationUser {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(length = 64) private String userName;
  @Column(length = 128) private String password;
  @Column(length = 128) private String fullName;
  @Column(length = 128) private String nickName;
  @Lob private byte[] avatar;

}
