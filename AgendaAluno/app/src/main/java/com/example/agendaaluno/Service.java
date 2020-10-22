package com.example.agendaaluno;

import java.util.List;

import retrofit.Call;
import retrofit.http.*;

public interface Service {
    @GET("getAluno")
    Call<List<Aluno>> selecionaTudo();

    @GET("getAluno/{RA}")
    Call<List<Aluno>> pegaRA(@Path("RA") String RA);

    @POST("insertAluno")
    Call<Aluno> incluirAluno(@Body Aluno aluno);

    @PUT("updateAluno/{RA}")
    Call<Aluno> alterarAluno(@Path("RA") String RA, @Body Aluno aluno);

    @DELETE("deleteAluno/{RA}")
    Call<Aluno> excluirAluno(@Path("RA") String RA);

}
