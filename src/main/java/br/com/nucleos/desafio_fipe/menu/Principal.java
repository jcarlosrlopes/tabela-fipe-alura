package br.com.nucleos.desafio_fipe.menu;

import br.com.nucleos.desafio_fipe.api.ConsumoAPI;
import java.util.Scanner;

public class Principal {

  private final String URL_API = "https://parallelum.com.br/fipe/api/v1/";
  private final String CARROS_ENDPOINT = "carros/marcas";
  private final String MOTOS_ENDPOINT = "carros/marcas";
  private final String CAMINHAO_ENDPOINT = "carros/marcas";

  private Scanner leitura = new Scanner(System.in);
  private ConsumoAPI consumoAPI = new ConsumoAPI();

  public void exibirMenu() {
    System.out.println("Que veículo você deseja pesquisar?");
    System.out.println("Carro, moto ou caminhão?");

    String escolha = leitura.nextLine();

    switch (escolha) {
      case "carro":
        consumoAPI.obterDados(URL_API + CARROS_ENDPOINT);
        break;
      case "moto":
        consumoAPI.obterDados(URL_API + MOTOS_ENDPOINT);
        break;
      case "caminhao":
        consumoAPI.obterDados(URL_API + CAMINHAO_ENDPOINT);
        System.out.println("Selecionado caminhao");
        break;
      default:
        break;
    }
  }
}
