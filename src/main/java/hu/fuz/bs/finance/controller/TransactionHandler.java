package hu.fuz.bs.finance.controller;

import hu.fuz.bs.finance.dao.financeitem.FinanceItemRepository;
import hu.fuz.bs.finance.exceptions.FinancialExceptions;
import hu.fuz.bs.finance.model.FinanceItem;
import hu.fuz.bs.finance.model.FinanceItem_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class TransactionHandler {

    @Autowired
    private FinanceItemRepository financeItemRepository;


    public void setBalanceAndOrderByLastFinanceItem(FinanceItem financeItem) {
        int lastOrderNumberAtTrDate = financeItemRepository.getMaxOrderNumberBefore(financeItem.getTransactionDate());
        Optional<FinanceItem> lastFinanceItem = financeItemRepository.getFinanceItemByOrderNumber(lastOrderNumberAtTrDate);

        if(!lastFinanceItem.isPresent()){
            // legelső elem
            financeItem.setOrderNumber(0);
        }else{
            financeItem.setOrderNumber(lastFinanceItem.get().getOrderNumber() + 1);
        }
    }

    void updateFinanceItemBalanceAnfOrderNumber() {
        Iterable<FinanceItem> updateableFinanceItem =
                financeItemRepository.findAll(null,
                        Sort.by(Sort.Order.asc(FinanceItem_.transactionDate.getName()),
                                Sort.Order.asc(FinanceItem_.orderNumber.getName())));

        BigDecimal lastBalance = BigDecimal.ZERO;
        int orderNumber = 0;
        for (FinanceItem item : updateableFinanceItem ) {
            // változtatjuk az egyenleget
            lastBalance = lastBalance.add(item.getAmount());
            item.setBalance(lastBalance);

            // növeljük a sorszámot
            item.setOrderNumber(orderNumber);
            orderNumber++;
        }
        financeItemRepository.saveAll(updateableFinanceItem);
    }

    public void changeTransactionOrder(FinanceItem first, FinanceItem second) {
        //csak akkor lehet cserélni, ha ugyanarra a napra esnek
        if(!first.getTransactionDate().equals(second.getTransactionDate())){
            throw new FinancialExceptions("Hátra- vagy előresorolás csak napon belül lehetséges!    ");
        }
        first.setOrderNumber(first.getOrderNumber() - 1);
        // 30 70    10 60+10=70
        // 10 40    30 40-10+30=60
        BigDecimal financeItemBalance = first.getBalance();
        first.setBalance(second.getBalance().subtract(second.getAmount()).add(first.getAmount()));

        second.setOrderNumber(second.getOrderNumber() + 1);
        second.setBalance(financeItemBalance);
    }
}
