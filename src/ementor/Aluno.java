package ementor;


import javax.swing.JOptionPane;


public class Aluno extends Pessoa{
    
    private long Matricula;
    private int Periodo;
    
    public Aluno(){        
        super();
        this.Matricula = 0;
        this.Periodo = 0;    
    }

    public Aluno(String nome, String data, long cpf, String contato ,long Matricula, int Periodo) {
        super(nome, data, cpf, contato);
        this.Matricula = Matricula;
        this.Periodo = Periodo;
    }
   
    public void setDados(String nome, String data, long cpf, String contato,long Matricula, int Periodo) {
        super.SetDados(nome, data, cpf, contato);
        this.Matricula = Matricula;
        this.Periodo = Periodo;
    }
    
    public long getMatricula(){
        return this.Matricula;       
    }
    
    public int getPeriodo(){        
        return this.Periodo;        
    }
    
    public void setMatricula(long matricula){       
        this.Matricula=matricula;       
    }
    
    public void setPeriodo(int periodo){        
        this.Periodo=periodo;        
    }
    
    public void imprimeDados(){        
        JOptionPane.showMessageDialog(null,"Nome: "+this.Nome+
                                           "\nCPF: "+this.CPF+
                                           "\nData de Nascimento: "+this.DataNascimento+
                                           "\nTelefone: "+this.Telefone+
                                           "\nMatricula: "+this.Matricula+
                                           "\nPeriodo: "+this.Periodo,"CADASTRO", JOptionPane.INFORMATION_MESSAGE);   
    }
    
}
