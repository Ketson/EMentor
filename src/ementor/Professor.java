package ementor;


import javax.swing.JOptionPane;


public class Professor extends Pessoa{
    
    private long ID;    
    private String DataAdmissao;
    private double SalarioBruto;

    public Professor() {
        super();
        this.DataAdmissao = "";
        this.SalarioBruto = 0.00;
    }


    public Professor(String nome, String data, long cpf, String contato,long ID,String DataAdmissao, double SalarioBruto) {
        super(nome, data, cpf, contato);
        this.ID = ID;
        this.DataAdmissao = DataAdmissao;
        this.SalarioBruto = SalarioBruto;
    }
    
    public void setDados(String nome, String data, long cpf, String contato,long ID,String DataAdmissao, double SalarioBruto) {
        super.SetDados(nome, data, cpf, contato);
        this.ID = ID;
        this.DataAdmissao = DataAdmissao;
        this.SalarioBruto = SalarioBruto;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setDataAdmissao(String DataAdmissao) {
        this.DataAdmissao = DataAdmissao;
    }

    public void setSalarioBruto(double SalarioBruto) {
        this.SalarioBruto = SalarioBruto;
    }
    
    
    
    public long getID(){        
        return this.ID;        
    }
    
    public String getDataAdmissao(){        
        return this.DataAdmissao;        
    }
    
    public double getSalarioBruto(){        
        return this.SalarioBruto;        
    }
    
    public void imprimeDados(){        
        JOptionPane.showMessageDialog(null,"Nome: "+this.Nome+
                                           "\nCPF: "+this.CPF+
                                           "\nData de Nascimento: "+this.DataNascimento+
                                           "\nTelefone: "+this.Telefone+
                                           "\nID: "+this.ID+
                                           "\nData de Admissao: "+this.DataAdmissao+
                                           "\nSalario Bruto: "+this.SalarioBruto,"CADASTRO", JOptionPane.INFORMATION_MESSAGE);   
    }
    
    public double getSalarioLiquido(){        
        double salBruto=0;
        
        salBruto = this.SalarioBruto*0.86;
        
        if(this.SalarioBruto>=5000)            
            salBruto = salBruto*0.775;
        
        return salBruto;
    }
    
}
