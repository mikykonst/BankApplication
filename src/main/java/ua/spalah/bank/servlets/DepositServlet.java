package ua.spalah.bank.servlets;

import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.services.AccountService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DepositServlet extends HttpServlet{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    ServletContext context = req.getSession().getServletContext();
    AccountService accountService = (AccountService) context.getAttribute("accountService");
    String idParam = req.getParameter("id");

    if (idParam != null) {
      long id = Long.parseLong(idParam);
      Account account = accountService.findAccountById(id);
      Double amount = Double.parseDouble(req.getParameter("amount"));
      long clientId = Long.parseLong(req.getParameter("clientId"));
      accountService.deposit(clientId, account, amount);
      resp.sendRedirect("/client/account?id=" + account.getId() + "&clientId=" + clientId);
    } else {
      resp.sendRedirect("/client");
    }
  }
}
