package com.example.agenda;

import java.util.List;

import retrofit.Call;
import retrofit.http.*;

public interface Service {
    @GET("get")
    Call<List<Aluno>> selecionaTudo();

    @GET("get/{RA}")
    Call<Aluno> pegaRA(@Path("RA") String RA);

    @POST("post")
    Call<Aluno> incluirAluno(@Body Aluno aluno);

    @PUT("put/{RA}")
    Call<Aluno> alterarAluno(@Path("RA") String RA, @Body Aluno aluno);

    @DELETE("delete/{RA}")
    Call<Aluno> excluirAluno(@Path("RA") String RA);

}
