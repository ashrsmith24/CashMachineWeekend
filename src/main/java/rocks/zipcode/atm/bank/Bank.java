package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Janae", "janaebryant@gmail.com", 2000
        )));
        accounts.put(4000, new BasicAccount(new AccountData(
                4000, "Ajah", "ajahdailey@gmail.com", 5000
        )));

        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Ashley", "cashash2@gmail.com", 2000000
        )));
        accounts.put(3000, new PremiumAccount(new AccountData(
                3000, "Wendy", "wen.dailey@gmail.com", 10000
        )));
    }


    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with id: " + id + "\nTry account 1000 or 2000");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
