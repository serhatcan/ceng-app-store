package tr.edu.metu.ceng352.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.metu.ceng352.model.BankTransaction;
import tr.edu.metu.ceng352.model.Purchase;
import tr.edu.metu.ceng352.model.PurchaseTransaction;
import tr.edu.metu.ceng352.model.User;
import tr.edu.metu.ceng352.repository.BankTransactionRepository;
import tr.edu.metu.ceng352.repository.PurchaseTransactionRepository;
import tr.edu.metu.ceng352.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Serhat CAN on 03.06.2015.
 */
@Service
public class TransactionService {

    @Autowired
    BankTransactionRepository bankTransactionRepository;

    @Autowired
    PurchaseTransactionRepository purchaseTransactionRepository;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public boolean addMoney(User user, String iban, Double amount) {
        BankTransaction bankTransaction = new BankTransaction(user, iban, amount);
        bankTransactionRepository.save(bankTransaction);
        userRepository.addMoney(user.getId(), amount);
        return true;
    }

    public List<BankTransaction> bankTransactions(Long userId) {
        return bankTransactionRepository.findAll(userId);
    }


    public List<PurchaseTransaction> purchaseTransactions(Long userId) {
        return purchaseTransactionRepository.findAll(userId);
    }

}
