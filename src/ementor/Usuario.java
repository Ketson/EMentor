
package ementor;


public class Usuario extends Pessoa {
    
    private String NomeUsuario;
    private String Senha;
    private int NivelAcesso;

    public Usuario() {
        this.NomeUsuario = "";
        this.Senha = "";
        this.NivelAcesso = 0;
    }

    public void setDados(String NomeUsuario, String Senha, int NivelAcesso) {
        this.NomeUsuario = NomeUsuario;
        this.Senha = Senha;
        this.NivelAcesso = NivelAcesso;
    }

    public void setNomeUsuario(String NomeUsuario) {
        this.NomeUsuario = NomeUsuario;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }
    
    
   
    public String getNomeUsuario() {
        return NomeUsuario;
    }

    public String getSenha() {
        return Senha;
    }
    
}
