package engtelecom.std.api_baralho.service;

import engtelecom.std.api_baralho.entities.Baralho;
import engtelecom.std.api_baralho.entities.Carta;

import java.util.*;

import org.springframework.stereotype.Component;

/**
 * Serviço para gerenciar baralhos de cartas.
 * 
 * Esta classe fornece métodos para criar, acessar, embaralhar, retirar cartas e excluir baralhos de uma coleção.
 */
@Component
public class BaralhoService {

    // Cria uma HashMap de baralhos
    private HashMap<String,Baralho> baralhos = new HashMap<>();


    /**
     * Retorna todos os identificadores de baralhos existentes.
     * 
     * @return Um conjunto contendo todos os identificadores de baralhos.
     */
    public Set<String> retornarTodosBaralhos(){
        return this.baralhos.keySet();
    }

    /**
     * Cria um novo baralho, inicializa suas cartas e adiciona na HashMap.
     * 
     * @return O identificador único do baralho criado.
     */
    public String criarBaralho(){
        String idBaralho = UUID.randomUUID().toString();
        Baralho novoBaralho = new Baralho(idBaralho);
        novoBaralho.iniciarCartas();
        this.baralhos.put(idBaralho, novoBaralho);
        return idBaralho;
    }

    /**
     * Retorna todas as cartas de um baralho específico.
     * 
     * @param id O identificador do baralho.
     * @return Uma lista contendo todas as cartas do baralho especificado.
     */
    public ArrayList<Carta> retornarTodasAsCartasDeBaralho(String id){
        return this.baralhos.get(id).getCartas();
    }

    /**
     * Embaralha as cartas de um baralho específico.
     * 
     * @param id O identificador do baralho.
     */
    public void embaralharBaralho(String id){
        this.baralhos.get(id).embaralharCartas();
    }

    /**
     * Retira um número específico de cartas de um baralho.
     * 
     * @param id O identificador do baralho.
     * @param n O número de cartas a serem retiradas.
     * @return Uma lista contendo as cartas retiradas do baralho.
     */
    public ArrayList<Carta> retirarNCartasDeBaralho(String id, Integer n){
        ArrayList<Carta> cartasRetiradas = this.baralhos.get(id).retirarNCartas(n);
        return cartasRetiradas;
    }

    /**
     * Exclui um baralho da coleção de baralhos.
     * 
     * @param id O identificador do baralho a ser excluído.
     * @return {@code true} se o baralho foi excluído com sucesso; {@code false} caso contrário.
     */
    public boolean excluirBaralho(String id){

        if(this.baralhos.containsKey(id)){
            this.baralhos.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Obtém o número de cartas restantes em um baralho específico.
     * 
     * @param id O identificador do baralho.
     * @return O número de cartas restantes no baralho.
     */
    public int obterCartasRestantes(String id){
        return this.baralhos.get(id).getCartas().size();
    }
    
    /**
     * Obtém a coleção de baralhos.
     * 
     * @return A coleção de baralhos.
     */
    public HashMap<String, Baralho> getBaralhos() {
        return baralhos;
    }

}
