package com.example.agendaaluno;

public class Aluno
{
    private String RA, Nome, Email;

    public Aluno (String RA, String Nome, String Email) throws Exception
    {
        this.setRA(RA);
        this.setNome(Nome);
        this.setEmail(Email);
    }

    public void setRA(String RA) throws Exception
    {
        if(RA == null || RA.length() > 5)
            throw new Exception("RA fora de tamanho");

        this.RA = RA;
    }

    public void setNome(String nome) throws Exception
    {
        if(nome == null || nome.length() > 50)
            throw new Exception("Nome fora de tamanho");

        this.Nome = nome;
    }

    public void setEmail(String email) throws Exception
    {
        if(email == null || email.length() > 50)
            throw new Exception("Email fora de tamanho");

        this.Email = email;
    }

    public String getRA()
    {
        return this.RA;
    }

    public String getNome()
    {
        return this.Nome;
    }

    public String getEmail()
    {
        return this.Email;
    }

    @Override
    public String toString()
    {
        return "RA: " + this.RA + "|| Nome: " + this.Nome + "|| Email:  " + this.Email;
    }
}
