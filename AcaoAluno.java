package br.unicamp.lanchoneteapi.classes;

import android.widget.*;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class AcaoAluno
{
    public static ArrayList<Aluno> GetAll()
    {
        ArrayList<Aluno> lista = null;

        try
        {
            String conteudo = HttpManager.getDados("ip:3000/getAluno");
            JSONArray jsonArray = new JSONArray(conteudo);
            lista = new ArrayList<>();

            for(int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                Aluno aluno = new Aluno(jsonObject.getString("RA"),
                                        jsonObject.getString("nome"),
                                        jsonObject.getString("email"));

                lista.add(aluno);
            }
        }
        catch (JSONException a)
        {
            a.printStackTrace();
        }
        catch (Exception a)
        {
            a.printStackTrace();
        }

        return lista;
    }

    public static Aluno getAluno(String RA)
    {
        Aluno ret = null;

        try
        {
            String conteudo = HttpManager.getDados("ip:3000/getAluno/" + RA);
            JSONObject jsonObject = new JSONObject(conteudo);

            ret = new Aluno(jsonObject.getString("RA"),
                            jsonObject.getString("nome"),
                            jsonObject.getString("email"));
        }
        catch (JSONException a)
        {
            a.printStackTrace();
        }
        catch (Exception a)
        {
            a.printStackTrace();
        }

        return ret;
    }

    public static String deleteAluno(String RA)
    {
        String ret = "Algo deu errado, tente novamente mais tarde";
        try
        {
            String conteudo = HttpManager.getDados("ip:3000/deleteAluno/" + RA);
            JSONObject resultado = new JSONObject(conteudo);

            ret = resultado.getString("mensagem");
        }
        catch (JSONException a)
        {
            a.printStackTrace();
        }

        return ret;
    }

    public static String insertAluno(Aluno aluno)
    {
        String ret = "Algo deu errado, tente novamente mais tarde";

        try
        {
            String conteudo = HttpManager.getDados("ip:3000/insertAluno/" + aluno.getRA() +
                                                                          "/" + aluno.getNome() +
                                                                          "/" + aluno.getEmail());
            JSONObject result = new JSONObject(conteudo);

            ret = result.getString("mensagem");
        }
        catch (JSONException a)
        {
            a.printStackTrace();
        }
        catch (Exception a)
        {
            a.printStackTrace();
        }

        return ret;
    }

    public static String updateAluno(Aluno aluno)
    {
        String ret = "Algo deu errado, tente novamente mais tarde";

        try
        {
            String conteudo = HttpManager.getDados("ip:3000/updateAluno/" + aluno.getRA() +
                                                                          "/" + aluno.getNome() +
                                                                          "/" + aluno.getEmail());

            JSONObject result = new JSONObject(conteudo);

            ret = result.getString("mensagem");
        }
        catch (JSONException a)
        {
            a.printStackTrace();
        }
        catch (Exception a)
        {
            a.printStackTrace();
        }

        return ret;
    }
}
