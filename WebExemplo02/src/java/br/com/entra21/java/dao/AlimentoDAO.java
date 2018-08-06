package br.com.entra21.java.dao;

import br.com.entra21.java.bean.AlimentoBean;
import br.com.entra21.java.database.Conexao;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Logan Michel
 */
public class AlimentoDAO {
    
    public List<AlimentoBean> obterTodos(){
        List<AlimentoBean> alimentos = new ArrayList<>();
        String sql = "SELECT * FROM alimentos";
        try {
            Statement st = Conexao.obterConexao().createStatement();
            st.execute(sql);
            ResultSet resultSet = st.getResultSet();
            while(resultSet.next()){
                AlimentoBean alimento = new AlimentoBean();
                alimento.setId(resultSet.getInt("id"));
                alimento.setNome(resultSet.getString("nome"));
                alimento.setPreco(resultSet.getDouble("preço"));
                alimento.setQuantidade(resultSet.getByte("quantidade"));
                alimentos.add(alimento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            Conexao.fecharConexao();
        }
        return alimentos;
    }
    
}
