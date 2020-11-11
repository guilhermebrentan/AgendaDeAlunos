package com.example.agendaaluno;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.IdentityHashMap;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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

    private static void CriarAluno(Aluno aluno, LinearLayout pai, final MainActivity contexto){
        LinearLayout l = new LinearLayout(contexto);
        l.setOrientation(LinearLayout.VERTICAL);
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

        li.addView(adicionar);
        li.addView(alterar);
        li.addView(deletar);

        LinearLayout lis = new LinearLayout(contexto);
        lis.setOrientation(LinearLayout.VERTICAL);
        lis.addView(l);
        lis.addView(li);

        pai.addView(lis);

        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup papai = (ViewGroup)view.getParent().getParent();
                ViewGroup v = (ViewGroup)papai.getChildAt(0);

                EditText edRA = (EditText)(v.getChildAt(0));
                EditText edNome = (EditText)(v.getChildAt(1));
                EditText edEmail = (EditText)(v.getChildAt(2));

                try
                {
                    Aluno aluno = new Aluno(edRA.getText().toString(), edNome.getText().toString(), edEmail.getText().toString());
                    Call<Aluno> call = new RetrofitConfig().getService().alterarAluno(aluno.getRA(), aluno);
                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Response<Aluno> response, Retrofit retrofit) {
                            if(response.isSuccess()){
                                Aluno alunoAlterado = response.body();
                                String ra = alunoAlterado.getRA();
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {}
                    });
                }
                catch(Exception e){}
            }
        });
        deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewGroup papai = (ViewGroup)view.getParent().getParent();
                ViewGroup v = (ViewGroup)papai.getChildAt(0);

                EditText edRA = (EditText)(v.getChildAt(0));
                EditText edNome = (EditText)(v.getChildAt(1));
                EditText edEmail = (EditText)(v.getChildAt(2));

                try
                {
                    Aluno aluno = new Aluno(edRA.getText().toString(), edNome.getText().toString(), edEmail.getText().toString());
                    Call<Aluno> call = new RetrofitConfig().getService().excluirAluno(aluno.getRA());
                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Response<Aluno> response, Retrofit retrofit) {
                            if(response.isSuccess()){
                                Aluno alunoAlterado = response.body();
                                String ra = alunoAlterado.getRA();
                            }
                            else{


                            }
                    }

                        @Override
                        public void onFailure(Throwable t) {}
                    });
                }
                catch(Exception e)
                {

                }
                papai.removeAllViews();
            }
        });
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ViewGroup papai = (ViewGroup)view.getParent().getParent();
                ViewGroup v = (ViewGroup)papai.getChildAt(0);

                EditText edRA = (EditText)(v.getChildAt(0));
                EditText edNome = (EditText)(v.getChildAt(1));
                EditText edEmail = (EditText)(v.getChildAt(2));

                try
                {
                    final Aluno aluno = new Aluno(edRA.getText().toString(), edNome.getText().toString(), edEmail.getText().toString());
                    Call<Aluno> call = new RetrofitConfig().getService().incluirAluno(aluno);
                    call.enqueue(new Callback<Aluno>() {
                        @Override
                        public void onResponse(Response<Aluno> response, Retrofit retrofit) {
                            if(response.isSuccess()){
                                Aluno alunoAlterado = response.body();
                                String ra = alunoAlterado.getRA();
                                ViewAlunos.MostrarAluno(aluno, (LinearLayout) papai.getParent(), contexto);
                            }
                        }

                        @Override
                        public void onFailure(Throwable t) {}
                    });
                }
                catch(Exception e)
                {

                }
            }
        });

    }
}
