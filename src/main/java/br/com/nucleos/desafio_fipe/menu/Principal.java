package br.com.nucleos.desafio_fipe.menu;

import br.com.nucleos.desafio_fipe.api.ConsumoAPI;

import br.com.nucleos.desafio_fipe.model.*;
import br.com.nucleos.desafio_fipe.service.ConverteDados;
import br.com.nucleos.desafio_fipe.service.ConverteDadosImpl;

import java.util.*;

public class Principal {

  private final String URL_API = "https://parallelum.com.br/fipe/api/v1/";
  private final String CARROS_ENDPOINT = "carros/marcas/";
  private final String MOTOS_ENDPOINT = "motos/marcas/";
  private final String CAMINHAO_ENDPOINT = "caminhoes/marcas/";
  private final String MODELOS_ENDPOINT = "/modelos/";
  private final String ANOS_ENDPOINT = "/anos/";

  private Scanner leitura = new Scanner(System.in);
  private ConsumoAPI consumoAPI = new ConsumoAPI();
  private ConverteDados conversor = new ConverteDadosImpl();

  public void exibirMenu() {
    String endereco = "";

    System.out.println("Que veículo você deseja pesquisar?");
    System.out.println("Carro, moto ou caminhão?");

    String escolha = leitura.nextLine();

    switch (escolha) {
      case "carro":
        endereco = URL_API + CARROS_ENDPOINT;
        break;
      case "moto":
        endereco = URL_API + MOTOS_ENDPOINT;
        break;
      case "caminhao":
        endereco = URL_API + CAMINHAO_ENDPOINT;
        break;
      default:
        break;
    }

    var json = consumoAPI.obterDados(endereco);
    var dadosMarcas = conversor.obterLista(json, Dados.class);

    dadosMarcas.stream()
            .sorted(Comparator.comparing(Dados::codigo))
            .forEach(m -> System.out.println("COD: " + m.codigo() + " - MARCA: " + m.nome()));
    System.out.println("Informe o código da marca para consulta: ");
    String codMarca = leitura.nextLine();

    endereco += codMarca + MODELOS_ENDPOINT;

    json = consumoAPI.obterDados(endereco);
    ModelosVeiculos modelos = conversor.obterDados(json, ModelosVeiculos.class);
    modelos.modelos().stream()
            .sorted(Comparator.comparing(Dados::codigo))
            .forEach(m -> System.out.println("Código: " + m.codigo() + " - Modelo: " + m.nome()));
    System.out.println("Digite um trecho do modelo você deseja consultar: ");
    String modelo = leitura.nextLine();

    modelos.modelos().
            stream().filter(m -> m.nome().toUpperCase().contains(modelo.toUpperCase()))
            .forEach(m -> System.out.println("Código: " + m.codigo() + " - Modelo: " + m.nome()));

    System.out.println("Digite o código do modelo para exibir os valores: ");
    String codModelo = leitura.nextLine();

    endereco += codModelo + ANOS_ENDPOINT;

    var jsonAnos = consumoAPI.obterDados(endereco);
    var dadosAnos = conversor.obterLista(jsonAnos, Dados.class);

    List<AvaliacaoVeiculo> avaliacoesDoVeiculo = new ArrayList<>();
    for (Dados ano : dadosAnos) {
      var jsonAvaliacao = consumoAPI.obterDados(endereco + ano.codigo());
      AvaliacaoVeiculo avaliacao = conversor.obterDados(jsonAvaliacao, AvaliacaoVeiculo.class);
      avaliacoesDoVeiculo.add(avaliacao);
    }

    avaliacoesDoVeiculo.forEach(System.out::println);
  }
}
