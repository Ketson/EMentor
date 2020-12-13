package ementor;


public class Pessoa{
    
    protected String Nome;
    protected String DataNascimento;
    protected long CPF;
    protected String Telefone;
    
    public Pessoa(){
        this.Nome = "";
        this.DataNascimento = "";
        this.CPF = 0;
        this.Telefone = "";
    }
    
    public Pessoa(String nome, String data, long cpf, String contato){
    
        this.Nome = nome;
        this.DataNascimento = data;
        this.CPF = cpf;
        this.Telefone = contato;
    }
    
    public void SetDados(String nome, String data, long cpf, String contato){
    
        this.Nome = nome;
        this.DataNascimento = data;
        this.CPF = cpf;
        this.Telefone = contato;
    }
}
