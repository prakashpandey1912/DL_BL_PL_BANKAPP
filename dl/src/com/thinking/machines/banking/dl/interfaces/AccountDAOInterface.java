package com.thinking.machines.banking.dl.interfaces;
import com.thinking.machines.banking.dl.exceptions.*;
import java.util.*;
public interface AccountDAOInterface
{
public void add(AccountDTOInterface accountDTOInterface) throws DAOException;
public void update(AccountDTOInterface accountDTOInterface) throws DAOException;
public void delete(int accountNumber) throws DAOException;
public AccountDTOInterface getByAccountNumber(int accountNumber) throws DAOException;
public AccountDTOInterface getByPANNumber(String panNumber) throws DAOException;
public List<AccountDTOInterface> getAll() throws DAOException;
public int getCount() throws DAOException;
}
