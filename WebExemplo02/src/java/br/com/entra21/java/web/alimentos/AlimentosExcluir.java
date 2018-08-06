package br.com.entra21.java.web.alimentos;

import br.com.entra21.java.dao.AlimentoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Logan Michel
 */
@WebServlet(urlPatterns = "/alimentos/excluir")
public class AlimentosExcluir  extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean apagou = new AlimentoDAO().excluir(id);
        
        if (apagou) {
            resp.getWriter().print("Apagou com sucesso");
        } else {
            resp.getWriter().print("Não foi possivel apagar");
        }
            
        }
    }