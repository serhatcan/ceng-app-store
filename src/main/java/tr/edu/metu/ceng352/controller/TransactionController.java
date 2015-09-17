/**
 * Created by Serhat CAN
 */
package tr.edu.metu.ceng352.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import tr.edu.metu.ceng352.model.BankTransaction;
import tr.edu.metu.ceng352.model.PurchaseTransaction;
import tr.edu.metu.ceng352.model.User;
import tr.edu.metu.ceng352.model.dbModel.TransactionLog;
import tr.edu.metu.ceng352.repository.UserRepository;
import tr.edu.metu.ceng352.service.AppService;
import tr.edu.metu.ceng352.service.CurrentUser;
import tr.edu.metu.ceng352.service.TransactionService;

import java.util.ArrayList;
import java.util.List;


@Controller
public class TransactionController extends MainController{


	@Autowired
	AppService appService;

	@Autowired
	CurrentUser currentUser;

	@Autowired
	TransactionService transactionService;

	@Autowired
	UserRepository userRepository;

	@RequestMapping(value = "/transactions", method = RequestMethod.GET)
	public String index(Model model) {
		User user = userRepository.findByEmail(currentUser.getUser().getEmail());
		currentUser.getUser().setMoney(user.getMoney());
		Double currentMoney = currentUser.getUser().getMoney();
		model.addAttribute("currentMoney", currentMoney);


		List<TransactionLog> transactions = new ArrayList<>();

		List<PurchaseTransaction> pt = transactionService.purchaseTransactions(user.getId());
		List<BankTransaction> bt = transactionService.bankTransactions(user.getId());
		for(PurchaseTransaction t: pt) {
			TransactionLog transaction = new TransactionLog(t.getId(), "Purchase", t.getAmount(), t.getTimeStamp());
			transactions.add(transaction);
		}
		for(BankTransaction t: bt) {
			TransactionLog transaction = new TransactionLog(t.getId(), "Bank", t.getAmount(), t.getTimeStamp());
			transactions.add(transaction);
		}
		model.addAttribute("transactions", transactions);
		return "admin/transaction";
	}

	@RequestMapping(value = "/transfer", params = {"iban", "money"}, method = RequestMethod.GET)
	public String transferMoney(@RequestParam(value = "iban") String iban, @RequestParam(value = "money") Double money) {
		transactionService.addMoney(currentUser.getUser(), iban, money);
		return "redirect:/transactions";
	}

}
