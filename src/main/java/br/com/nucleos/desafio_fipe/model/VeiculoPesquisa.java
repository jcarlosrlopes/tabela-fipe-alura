package br.com.nucleos.desafio_fipe.model;

public enum VeiculoPesquisa {
  CARRO("carro"),
  MOTO("moto"),
  CAMINHAO("caminhao");

  private String nome;

  VeiculoPesquisa(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return this.nome;
  }
}
