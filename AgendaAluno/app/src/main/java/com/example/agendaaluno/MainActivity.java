package com.example.agendaaluno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {
    LinearLayout layout, pai;
    ScrollView scroll;
    EditText pesquisaRA;
    MainActivity contexto;
    Button btnBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            layout = (LinearLayout) findViewById(R.id.layout);
            pesquisaRA = (EditText) findViewById(R.id.edPesquisaRa);
            pai = (LinearLayout) findViewById(R.id.pai);
            scroll = (ScrollView) findViewById(R.id.scroll);
            btnBuscar = (Button) findViewById(R.id.btnBuscar);
            contexto = this;

            try {
                ArrayList<Aluno> alunos = new ArrayList<Aluno>();
                alunos.add(new Aluno("19817", "djhgs", "kdxj"));
                alunos.add(new Aluno("19817", "djhgs", "kdxj"));

                ViewAlunos.MostrarAlunos(alunos, pai, this);
            } catch (Exception e) {
            }

            try {
                btnBuscar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {
                            if (pesquisaRA.getText().toString() == "") {
                                Call<List<Aluno>> call = new RetrofitConfig().getService().selecionaTudo();


                                call.enqueue(new Callback<List<Aluno>>() {
                                    @Override
                                    public void onResponse(Response<List<Aluno>> response, Retrofit retrofit) {
                                        if (response.isSuccess()) {
                                            List<Aluno> listaAluno = new ArrayList<Aluno>();
                                            listaAluno = response.body();
                                            ViewAlunos.MostrarAlunos((ArrayList<Aluno>) listaAluno, pai, contexto);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Erro de conexão! Tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                            else
                            {
                                Call<List<Aluno>> call = new RetrofitConfig().getService().pegaRA(pesquisaRA.getText().toString());


                                call.enqueue(new Callback<List<Aluno>>() {
                                    @Override
                                    public void onResponse(Response<List<Aluno>> response, Retrofit retrofit) {
                                        if (response.isSuccess()) {
                                            List<Aluno> listaAluno = new ArrayList<Aluno>();
                                            listaAluno = response.body();
                                            ViewAlunos.MostrarAlunos((ArrayList<Aluno>) listaAluno, pai, contexto);
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Erro de conexão! Tente novamente mais tarde!", Toast.LENGTH_LONG).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Throwable t) {
                                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }
                        catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
            catch(Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(), e.getMessage().toString(), Toast.LENGTH_LONG).show();
        }
    }
}