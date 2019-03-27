package hu.fuz.bs.core.dao;

import hu.fuz.bs.core.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Long> {
  Optional<ApplicationUser> findByUserName(String userName);
}
