package br.com.entra21.java.dao;

import br.com.entra21.java.bean.AlimentoBean;
import br.com.entra21.java.database.Conexao;
import com.sun.xml.ws.tx.at.v10.types.PrepareResponse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Logan Michel
 */
public class AlimentoDAO {

    public List<AlimentoBean> obterTodos() {
        List<AlimentoBean> alimentos = new ArrayList<>();
        String sql = "SELECT * FROM alimentos";
        try {
            Statement st = Conexao.obterConexao().createStatement();
            st.execute(sql);
            ResultSet resultSet = st.getResultSet();
            while (resultSet.next()) {
                AlimentoBean alimento = new AlimentoBean();
                alimento.setId(resultSet.getInt("id"));
                alimento.setNome(resultSet.getString("nome"));
                alimento.setPreco(resultSet.getDouble("preco"));
                alimento.setQuantidade(resultSet.getByte("quantidade"));
                alimentos.add(alimento);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao();
        }
        return alimentos;
    }

    public int adicionar(AlimentoBean alimento) {
        String sql = "INSERT INTO alimentos (nome, quantidade, preco, descricao) VALUES (?, ?, ?, ?)";
        try {

            PreparedStatement ps = Conexao.obterConexao().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            int quantidade = 1;
            ps.setString(quantidade++, alimento.getNome());
            ps.setByte(quantidade++, alimento.getQuantidade());
            ps.setDouble(quantidade++, alimento.getPreco());
            ps.setString(quantidade++, alimento.getDescricao());
            ps.execute();
            ResultSet resultSet = ps.getGeneratedKeys();
            if (resultSet.next());
            return resultSet.getInt(1);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Conexao.fecharConexao();
        }
        return -1;
    }
    
    public boolean excluir(int id){
        String sql = "DELETE FROM alimentos WHERE id = ?";
        try {
            PreparedStatement ps = Conexao.obterConexao().prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            Conexao.fecharConexao();
        }
        return false;
    }

    public AlimentoBean obterPeloId(int id) {
        String sql = "SELECT * FROM alimentos WHERE id = ?";
        try {
            PreparedStatement ps = Conexao.obterConexao().prepareStatement(sql);
        ps.setInt(1, id);
        ps.execute();
        ResultSet resultSet = ps.getResultSet();
        if (resultSet.next()) {
            AlimentoBean alimento = new AlimentoBean();
            alimento.setId(id);
            alimento.setNome(resultSet.getString("nome"));
            alimento.setPreco(resultSet.getDouble("preco"));
            alimento.setQuantidade(resultSet.getByte("quantidade"));
            alimento.setDescricao(resultSet.getString("descricao"));
            return alimento;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            Conexao.fecharConexao();
        }
        return null;
    }

    public boolean alterar(AlimentoBean alimento) {
        
        String sql = "UPDATE alimentos SET nome = ?, descricao = ?, preco = ?, quantidade = ? WHERE id = ?";
        try {
            PreparedStatement ps = Conexao.obterConexao().prepareStatement(sql);
            ps.setString(1, alimento.getNome());
            ps.setString(2, alimento.getDescricao());
            ps.setDouble(3, alimento.getPreco());
            ps.setByte(4, alimento.getQuantidade());
            ps.setInt(5, alimento.getId());
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            Conexao.fecharConexao();
        }
        return false;
    }
}
