import com.thinking.machines.banking.dl.exceptions.*;
import com.thinking.machines.banking.dl.interfaces.*;
import com.thinking.machines.banking.dl.*;
public class AccountAddTestCase
{
public static void main(String gg[])
{
AccountDTOInterface accountDTOInterface=new AccountDTO();
accountDTOInterface.setFirstName(gg[0]);
accountDTOInterface.setLastName(gg[1]);
accountDTOInterface.setGender(gg[2]);
accountDTOInterface.setPANNumber(gg[3]);
try
{
AccountDAOInterface accountDAOInterface=new AccountDAO();
accountDAOInterface.add(accountDTOInterface);
System.out.println("Account added Account NO.:"+accountDTOInterface.getAccountNumber());
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}