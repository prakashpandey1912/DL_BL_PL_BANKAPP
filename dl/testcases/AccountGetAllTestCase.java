import com.thinking.machines.banking.dl.exceptions.*;
import com.thinking.machines.banking.dl.interfaces.*;
import com.thinking.machines.banking.dl.*;
import java.util.*;
public class AccountGetAllTestCase
{
public static void main(String gg[])
{
try
{
AccountDAOInterface accountDAOInterface=new AccountDAO();
List<AccountDTOInterface> list=accountDAOInterface.getAll();
for(AccountDTOInterface accountDTOInterface:list)
{
System.out.println(accountDTOInterface.getAccountNumber());
System.out.println(accountDTOInterface.getFirstName());
System.out.println(accountDTOInterface.getLastName());
System.out.println(accountDTOInterface.getGender());
System.out.println(accountDTOInterface.getPANNumber());
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
}
}