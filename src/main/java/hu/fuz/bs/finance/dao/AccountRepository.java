package hu.fuz.bs.finance.dao;

import hu.fuz.bs.finance.model.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account,Long> {
    Optional<Account> findAccountByOrderNumber(int orderNumber);
}
