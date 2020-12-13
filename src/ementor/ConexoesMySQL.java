package ementor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.ResultSet;

public class ConexoesMySQL {

    //private String driveJDBC = "com.mysql.cj.jdbc.Driver"; //nome dado pela oracle ao drive
    private String caminho = "localhost"; //indica que usaremos o server na maquina
    private String porta = "3306"; //porta padrao de conexao do MySQL server
    private String nome = "ementorteste"; //nome da base de dados no sql         
    private String usuario = "root"; //nome padrao do MySQL
    private String senha = "admin"; //senha do MySQL que cadastrei
    private String FusoHorario = "?useTimezone=true&serverTimezone=UTC";
    
    private String URL = "jdbc:mysql://" + caminho + ":" + porta + "/" + nome + FusoHorario;
    
    public Connection realizaConexaoMySQL() {
        try {
            return DriverManager.getConnection(URL, usuario, senha);//Estabelece a conexão 
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
    }
    
    public void desconectaMySQL(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
    }
    
    public void insereDadosNoMySQL(String nome, long CPF, String contato, String Data, long matricula, int periodo) {
        Connection conexao = realizaConexaoMySQL();
        String sql_pessoa = "insert into pessoa(CPF, Nome, Telefone, DataNascimento)values (?,?,?,?)";
        String sql_aluno = "insert into aluno(Matricula, CPF_Pessoa, Periodo) values(?,?,?) ";
        
        try {
            PreparedStatement atuador_pessoa = conexao.prepareStatement(sql_pessoa);
            atuador_pessoa.setLong(1, CPF);
            atuador_pessoa.setString(2, nome);
            atuador_pessoa.setString(3, contato);
            atuador_pessoa.setString(4, Data);
            atuador_pessoa.execute();
            
            PreparedStatement atuador_aluno = conexao.prepareStatement(sql_aluno);
            atuador_aluno.setLong(1, matricula);
            atuador_aluno.setLong(2, CPF);
            atuador_aluno.setInt(3, periodo);
            atuador_aluno.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
        
        desconectaMySQL(conexao);
    }
    
    public void insereDadosNoMySQLProfessor(String nome, long CPF, String contato, String Data, long ID, String DataAdmissao, double SalarioBruto) {
        Connection conexao = realizaConexaoMySQL();
        String sql_pessoa = "insert into pessoa(CPF, Nome, Telefone, DataNascimento)values (?,?,?,?)";
        String sql_professor = "insert into professor(ID, CPF_Pessoa, DataAdmissao, SalarioBruto) values(?,?,?,?) ";
        
        try {
            PreparedStatement atuador_pessoa = conexao.prepareStatement(sql_pessoa);
            atuador_pessoa.setLong(1, CPF);
            atuador_pessoa.setString(2, nome);
            atuador_pessoa.setString(3, contato);
            atuador_pessoa.setString(4, Data);
            atuador_pessoa.execute();
            
            PreparedStatement atuador_professor = conexao.prepareStatement(sql_professor);
            atuador_professor.setLong(1, ID);
            atuador_professor.setLong(2, CPF);
            atuador_professor.setString(3, DataAdmissao);
            atuador_professor.setDouble(4, SalarioBruto);
            atuador_professor.execute();
            
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Salvar", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
        
        desconectaMySQL(conexao);
    }
    
    public ArrayList<Aluno> recuperaDadosDoMySQL(String tipoOrdenacao) {
        Connection conexao = realizaConexaoMySQL();//estabelece conexao
        ArrayList<Aluno> Academico = new ArrayList();
        
        try {
            
            String sql_selecao_aluno = "SELECT *FROM ementorteste.pessoa, ementorteste.aluno WHERE pessoa.CPF=aluno.CPF_Pessoa ORDER BY "+tipoOrdenacao+";";
            PreparedStatement atuador_selecao_aluno = conexao.prepareStatement(sql_selecao_aluno);
            ResultSet ResultadoSelecao = atuador_selecao_aluno.executeQuery(); //aqui que fica o resultado da seleçao do mysql
            
            while (ResultadoSelecao.next()) {
                Aluno ObjetoAluno = new Aluno();
                
                ObjetoAluno.CPF = ResultadoSelecao.getLong("CPF");
                ObjetoAluno.Nome = ResultadoSelecao.getString("Nome");
                ObjetoAluno.DataNascimento = ResultadoSelecao.getString("DataNascimento");
                ObjetoAluno.Telefone = ResultadoSelecao.getString("Telefone");
                ObjetoAluno.setMatricula(ResultadoSelecao.getLong("Matricula"));
                ObjetoAluno.setPeriodo(ResultadoSelecao.getInt("Periodo"));
                
                Academico.add(ObjetoAluno);
                
            }
            
            ResultadoSelecao.close();
            atuador_selecao_aluno.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
        
        desconectaMySQL(conexao);
        
        return Academico;
    }
    
    public ArrayList<Professor> recuperaDadosDoMySQLProfessor(String tipoOrdenacao) {
        Connection conexao = realizaConexaoMySQL();//estabelece conexao
        ArrayList<Professor> Magister = new ArrayList();
        
        try {
            
            String sql_selecao_professor = "SELECT *FROM ementorteste.pessoa, ementorteste.professor WHERE pessoa.CPF=professor.CPF_Pessoa ORDER BY "+tipoOrdenacao+";";
            PreparedStatement atuador_selecao_professor = conexao.prepareStatement(sql_selecao_professor);
            ResultSet ResultadoSelecao = atuador_selecao_professor.executeQuery(); //aqui que fica o resultado da seleçao do mysql
            
            while (ResultadoSelecao.next()) {
                Professor ObjetoProfessor = new Professor();
                
                ObjetoProfessor.CPF = ResultadoSelecao.getLong("CPF");
                ObjetoProfessor.Nome = ResultadoSelecao.getString("Nome");
                ObjetoProfessor.DataNascimento = ResultadoSelecao.getString("DataNascimento");
                ObjetoProfessor.Telefone = ResultadoSelecao.getString("Telefone");
                ObjetoProfessor.setID(ResultadoSelecao.getLong("ID"));
                ObjetoProfessor.setDataAdmissao(ResultadoSelecao.getString("DataAdmissao"));
                ObjetoProfessor.setSalarioBruto(ResultadoSelecao.getDouble("SalarioBruto"));
                
                Magister.add(ObjetoProfessor);
                
            }
            
            ResultadoSelecao.close();
            atuador_selecao_professor.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
        
        desconectaMySQL(conexao);
        
        return Magister;
    }
    
    public Aluno buscaAluno(String matr){
        Connection conexao = realizaConexaoMySQL();
        Aluno Academico = new Aluno();
        Academico=null;
        
        try{
            String sql_selecao_aluno = "SELECT *FROM ementorteste.pessoa,ementorteste.aluno where pessoa.CPF=aluno.CPF_Pessoa and aluno.Matricula="+matr+";";
            PreparedStatement atuador_selecao_aluno= conexao.prepareStatement(sql_selecao_aluno);
            ResultSet ResultadoSelecao = atuador_selecao_aluno.executeQuery();
            
            while(ResultadoSelecao.next()){
                Aluno ObjetoAluno= new Aluno();
                ObjetoAluno.CPF = ResultadoSelecao.getLong("CPF");
                ObjetoAluno.Nome = ResultadoSelecao.getString("Nome");
                ObjetoAluno.DataNascimento = ResultadoSelecao.getString("DataNascimento");
                ObjetoAluno.Telefone = ResultadoSelecao.getString("Telefone");
                ObjetoAluno.setMatricula(ResultadoSelecao.getLong("Matricula"));
                ObjetoAluno.setPeriodo(ResultadoSelecao.getInt("Periodo"));
                
                Academico = ObjetoAluno;
                
                
            }
            ResultadoSelecao.close();
            atuador_selecao_aluno.close();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
        
        desconectaMySQL(conexao);
        
        return Academico;
    }
    
    public Professor buscaProfessor(String ID){
        Connection conexao = realizaConexaoMySQL();
        Professor Magister = new Professor();
        Magister=null;
        
        try{
            String sql_selecao_professor = "SELECT *FROM ementorteste.pessoa,ementorteste.professor where pessoa.CPF=professor.CPF_Pessoa and professor.ID="+ID+";";
            PreparedStatement atuador_selecao_professor= conexao.prepareStatement(sql_selecao_professor);
            ResultSet ResultadoSelecao = atuador_selecao_professor.executeQuery();
            
            while(ResultadoSelecao.next()){
                
                Professor ObjetoProfessor= new Professor();
                
                ObjetoProfessor.CPF = ResultadoSelecao.getLong("CPF");
                ObjetoProfessor.Nome = ResultadoSelecao.getString("Nome");
                ObjetoProfessor.DataNascimento = ResultadoSelecao.getString("DataNascimento");
                ObjetoProfessor.Telefone = ResultadoSelecao.getString("Telefone");
                ObjetoProfessor.setID(ResultadoSelecao.getLong("ID"));
                ObjetoProfessor.setDataAdmissao(ResultadoSelecao.getString("DataAdmissao"));
                ObjetoProfessor.setSalarioBruto(ResultadoSelecao.getDouble("SalarioBruto"));
                
                Magister = ObjetoProfessor;
                
                
            }
            ResultadoSelecao.close();
            atuador_selecao_professor.close();
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
        
        desconectaMySQL(conexao);
        
        return Magister;
    }
    
    public void atualizaDadosNoMySQL(String matr, String nome, String dataNascimento, String telefone, String per){
        
        Connection conexao = realizaConexaoMySQL();
        String sql = "update ementorteste.pessoa,ementorteste.aluno set Nome= '"+nome+"' ,Periodo="+per+", DataNascimento='"+dataNascimento+"', Telefone="+telefone+" where pessoa.CPF=aluno.CPF_Pessoa and aluno.Matricula="+matr+";";
        //para alterar string usa '"+nome"'
        try{
            PreparedStatement atuador = conexao.prepareStatement(sql);
            atuador.executeUpdate();
            atuador.close();
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso"  , "DADOS ATUALIZADOS", JOptionPane.INFORMATION_MESSAGE);
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
        
        desconectaMySQL(conexao);
        
        
    }
    
    public void atualizaDadosNoMySQLProfessor(String ID, String nome,String dataadm,String salb, String dataNascimento, String telefone){
        
        Connection conexao = realizaConexaoMySQL();
        String sql = "update ementorteste.pessoa,ementorteste.professor set Nome= '"+nome+"' , DataAdmissao='"+dataadm+"', SalarioBruto= "+salb+", DataNascimento='"+dataNascimento+"', Telefone="+telefone+" where pessoa.CPF=professor.CPF_Pessoa and professor.ID="+ID+";";
        //para alterar string usa '"+nome"'
        try{
            PreparedStatement atuador = conexao.prepareStatement(sql);
            atuador.executeUpdate();
            atuador.close();
            JOptionPane.showMessageDialog(null, "Dados atualizados com sucesso"  , "DADOS ATUALIZADOS", JOptionPane.INFORMATION_MESSAGE);
            
        }catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
        
        desconectaMySQL(conexao);
        
        
    }
    
        
    public Usuario recuperaUsuarioeSenha(String user, String pass) {
        Connection conexao = realizaConexaoMySQL();//estabelece conexao
        Usuario Login = new Usuario();
        Login=null;
        
        try {
            
            String sql_selecao_usuario = "SELECT *FROM ementorteste.usuario where usuario.Usuario='"+user+"' and usuario.Senha= '"+pass+"';";
            PreparedStatement atuador_selecao_usuario = conexao.prepareStatement(sql_selecao_usuario);
            ResultSet ResultadoSelecao = atuador_selecao_usuario.executeQuery(); //aqui que fica o resultado da seleçao do mysql
            
            while (ResultadoSelecao.next()) {
                Usuario ObjetoUsuario = new Usuario();
                
                ObjetoUsuario.setNomeUsuario(ResultadoSelecao.getString("Usuario"));
                ObjetoUsuario.setSenha(ResultadoSelecao.getString("Senha"));
                
                Login=ObjetoUsuario;
   
                
            }
            
            ResultadoSelecao.close();
            atuador_selecao_usuario.close();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Algum imprevisto occoreu" + e + "", "ERRO", JOptionPane.ERROR_MESSAGE);
            
        }
        
        desconectaMySQL(conexao);
        
        return Login;
    }
    
}
