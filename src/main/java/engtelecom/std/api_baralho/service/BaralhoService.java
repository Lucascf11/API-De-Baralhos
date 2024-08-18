package engtelecom.std.api_baralho.service;

import engtelecom.std.api_baralho.entities.Baralho;
import engtelecom.std.api_baralho.entities.Carta;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class BaralhoService {

    // Cria uma HashMap de baralhos
    private HashMap<String,Baralho> baralhos = new HashMap<>();


    // Método que retorna todos os baralhos da tabela Hash de baralhos
    public Set<String> retornarTodosBaralhos(){
        return this.baralhos.keySet();
    }

    // Método que cria um baralho novo e adiciona na HashMap, cuja chave é um UUID, que atua como ID desse baralho em questão
    public String criarBaralho(){
        String idBaralho = UUID.randomUUID().toString();
        Baralho novoBaralho = new Baralho(idBaralho);
        novoBaralho.iniciarCartas();
        this.baralhos.put(idBaralho, novoBaralho);
        return idBaralho;
    }

    // Procura um baralho na HashMap e retorna todas as cartas dele
    public ArrayList<Carta> retornarTodasAsCartasDeBaralho(String id){
        return this.baralhos.get(id).getCartas();
    }

    // Procura um baralho na HashMap e embaralha ele
    public void embaralharBaralho(String id){
        this.baralhos.get(id).embaralharCartas();
    }

    // Procura um baralho na HashMap e retira "n" cartas dele
    public ArrayList<Carta> retirarNCartasDeBaralho(String id, Integer n){
        ArrayList<Carta> cartasRetiradas = this.baralhos.get(id).retirarNCartas(n);
        return cartasRetiradas;
    }

    // Procura um baralho na HashMap e o exclui da estrutura de dados
    public boolean excluirBaralho(String id){

        if(this.baralhos.containsKey(id)){
            this.baralhos.remove(id);
            return true;
        }
        return false;
    }

    public int obterCartasRestantes(String id){
        return this.baralhos.get(id).getCartas().size();
    }

    public HashMap<String, Baralho> getBaralhos() {
        return baralhos;
    }

}
