package hu.fuz.bs.core.dao;

import hu.fuz.bs.core.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationUserRepository extends CrudRepository<ApplicationUser,Long> {

}
