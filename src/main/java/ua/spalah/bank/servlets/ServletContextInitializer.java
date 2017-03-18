package ua.spalah.bank.servlets;

import ua.spalah.bank.dao.impl.AccountDaoHiber;
import ua.spalah.bank.dao.impl.ClientDaoHiber;
import ua.spalah.bank.services.AccountService;
import ua.spalah.bank.services.BankReportService;
import ua.spalah.bank.services.ClientService;
import ua.spalah.bank.services.impl.AccountServiceImpl;
import ua.spalah.bank.services.impl.BankReportServiceImpl;
import ua.spalah.bank.services.impl.ClientServiceImpl;


import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextInitializer implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent servletContextEvent) {
    try {


      EntityManagerFactory managerFactory = Persistence.createEntityManagerFactory("BankApplication");
      ClientDaoHiber clientDao = new ClientDaoHiber(managerFactory);
      AccountDaoHiber accountDao = new AccountDaoHiber(managerFactory);
      ClientService clientService = new ClientServiceImpl(clientDao, accountDao);
      AccountService accountService = new AccountServiceImpl(clientDao, accountDao);
      BankReportService bankReportService = new BankReportServiceImpl(clientDao,accountDao);
      ServletContext context = servletContextEvent.getServletContext();
      context.setAttribute("clientService",clientService);
      context.setAttribute("accountService",accountService);
      context.setAttribute("bankReportService",bankReportService);
    } catch (Exception e) {
//            throw new RuntimeException("Initialization error", e);
      RuntimeException ex = new RuntimeException("Initialization error");
      ex.initCause(e);
      throw ex;
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent servletContextEvent) {

  }

}
