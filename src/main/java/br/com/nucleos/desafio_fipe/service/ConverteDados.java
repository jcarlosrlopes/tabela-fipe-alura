package br.com.nucleos.desafio_fipe.service;

import java.util.List;

public interface ConverteDados {

    <T> T obterDados(String json, Class<T> classe);

    <T> List<T> obterLista(String json, Class<T> classe);
}
