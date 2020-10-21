package com.example.agendaaluno;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.IdentityHashMap;

public class ViewAlunos {
    public static void MostrarAlunos(ArrayList<Aluno> alunos, LinearLayout pai, MainActivity contexto){
        pai.removeAllViews();
        for(int i = 0; i < alunos.size(); i++){
            CriarAluno(alunos.get(i), pai, contexto);
        }
    }

    public static void MostrarAluno(Aluno aluno, LinearLayout pai, MainActivity contexto){
        pai.removeAllViews();
        CriarAluno(aluno, pai, contexto);
    }

    private static void CriarAluno(Aluno aluno, LinearLayout pai, MainActivity contexto){
        LinearLayout l = new LinearLayout(contexto);
        l.setOrientation(LinearLayout.HORIZONTAL);
        EditText ra = new EditText(contexto);
        ra.setText(aluno.getRA());
        EditText nome = new EditText(contexto);
        nome.setText(aluno.getNome());
        EditText email = new EditText(contexto);
        email.setText(aluno.getEmail());
        l.addView(ra);
        l.addView(nome);
        l.addView(email);

        LinearLayout li = new LinearLayout(contexto);
        li.setOrientation(LinearLayout.HORIZONTAL);
        Button alterar = new Button(contexto);
        Button deletar = new Button(contexto);
        Button adicionar = new Button(contexto);
        alterar.setText("Alterar");
        adicionar.setText("Adicionar");
        deletar.setText("Deletar");
        /*alterar.setTextSize(8);
        deletar.setTextSize(8);
        adicionar.setTextSize(8);*/
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        li.addView(adicionar);
        li.addView(alterar);
        li.addView(deletar);

        LinearLayout lis = new LinearLayout(contexto);
        lis.setOrientation(LinearLayout.VERTICAL);
        lis.addView(l);
        lis.addView(li);

        pai.addView(lis);
    }
}
