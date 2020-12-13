package ementor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Arquivos implements Serializable {
    
    public void salvarNoDisco(Object[] meuObjeto, String nomeArquivo){
        
        File arquivo = new File(nomeArquivo); //cria fisicamente o arquivo
        
        try{
            FileOutputStream fout = new FileOutputStream(arquivo); //criacao de saida de arquivo
            ObjectOutputStream ObjetoGravacao = new ObjectOutputStream(fout); //criacao de saida de objeto
            
            ObjetoGravacao.writeObject(meuObjeto);
            
            ObjetoGravacao.flush(); //limpa buffer
            ObjetoGravacao.close(); //fecha o objeto
            fout.close(); //fecha arquivo
        }
        
        catch(Exception ex){
            System.out.println("ERRO: "+ex.toString());
        }
    }
    
    public Object[] lerDoDisco(Object[] vetor, String nomeArquivo){
        
        File arquivo = new File(nomeArquivo); //cria fisicamente o arquivo
        
        try{            
            FileInputStream arquivoFisico = new FileInputStream(arquivo);
            ObjectInputStream ObjetoEntrada = new ObjectInputStream(arquivoFisico);
            
            vetor = (Object []) ObjetoEntrada.readObject();
            
            ObjetoEntrada.close();
            arquivoFisico.close();
            
        }        
        catch(Exception ex){
            System.out.println("ERRO: "+ex.toString());
        }        
        return vetor;        
    }
    
    public void salvarNoDiscoIndice(int indice, String nomeArquivo){
        
        File arquivo = new File(nomeArquivo); //cria fisicamente o arquivo
        
        try{
            FileOutputStream fout = new FileOutputStream(arquivo); //criacao de saida de arquivo
            ObjectOutputStream ObjetoGravacao = new ObjectOutputStream(fout); //criacao de saida de objeto
            
            ObjetoGravacao.writeObject(indice);
            
            ObjetoGravacao.flush(); //limpa buffer
            ObjetoGravacao.close(); //fecha o objeto
            fout.close(); //fecha arquivo
        }        
        catch(Exception ex){
            System.out.println("ERRO: "+ex.toString());
        }
    }
    
    public int lerDoDiscoIndice(String nomeArquivo){
        
        File arquivo = new File(nomeArquivo); //cria fisicamente o arquivo
        int i = 0;
        
        try{            
            FileInputStream arquivoFisico = new FileInputStream(arquivo);
            ObjectInputStream ObjetoEntrada = new ObjectInputStream(arquivoFisico);
            
            i = (int) ObjetoEntrada.readObject();
            
            ObjetoEntrada.close();
            arquivoFisico.close();
            
        }        
        catch(Exception ex){
            System.out.println("ERRO: "+ex.toString());
        }        
        return i;
        
    }
    
}
