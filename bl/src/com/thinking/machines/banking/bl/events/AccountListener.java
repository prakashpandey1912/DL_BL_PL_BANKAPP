package com.thinking.machines.banking.bl.events;
import com.thinking.machines.banking.bl.events.*;
public interface  AccountListener
{
public void accountAdded(AccountEvent event);
public void accountUpdated(AccountEvent event);
public void accountDeleted(AccountEvent event);
} 


