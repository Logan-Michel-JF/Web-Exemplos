package br.com.entra21.java.web.alimentos;

import br.com.entra21.java.bean.AlimentoBean;
import br.com.entra21.java.dao.AlimentoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Logan Michel
 */
@WebServlet(urlPatterns = "/alimentos/store")
public class AlimentosStore extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AlimentoBean alimento = new AlimentoBean();
        alimento.setNome(req.getParameter("nome"));
        alimento.setQuantidade(Byte.parseByte(req.getParameter("quantidade")));
        alimento.setPreco(Double.parseDouble(req.getParameter("preco")));
        alimento.setDescricao(req.getParameter("descricao"));
        
        int codigo = new AlimentoDAO().adicionar(alimento);
        
        if(codigo > 0){
            resp.sendRedirect("/WebExemplo02/alimentos");
        }
    }
    
}
