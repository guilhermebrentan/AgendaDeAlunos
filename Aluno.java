package br.unicamp.lanchoneteapi.classes;

public class Aluno
{
    private String RA, nome, email;

    public Aluno (String RA, String nome, String email) throws Exception
    {
        this.setRA(RA);
        this.setNome(nome);
        this.setEmail(email);
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

        this.nome = nome;
    }

    public void setEmail(String email) throws Exception
    {
        if(email == null || email.length() > 50)
            throw new Exception("Email fora de tamanho");

        this.email = email;
    }

    public String getRA()
    {
        return this.RA;
    }

    public String getNome()
    {
        return this.nome;
    }

    public String getEmail()
    {
        return this.email;
    }

    @Override
    public String toString()
    {
        return "RA: " + this.RA + "|| Nome: " + this.nome + "|| Email:  " + this.email;
    }
}
