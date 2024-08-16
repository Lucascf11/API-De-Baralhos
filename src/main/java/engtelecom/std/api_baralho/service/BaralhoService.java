package engtelecom.std.api_baralho.service;

import engtelecom.std.api_baralho.entities.Baralho;
import engtelecom.std.api_baralho.entities.Carta;

import java.util.*;

import org.springframework.stereotype.Component;

@Component
public class BaralhoService {

    private HashMap<String,Baralho> baralhos = new HashMap<>();


    public Set<String> retornarTodosBaralhos(){
        return this.baralhos.keySet();
    }

    public String criarBaralho(){
        String idBaralho = UUID.randomUUID().toString();
        Baralho novoBaralho = new Baralho(idBaralho);
        novoBaralho.iniciarCartas();
        this.baralhos.put(idBaralho, novoBaralho);
        return idBaralho;
    }

    public ArrayList<Carta> retornarTodasAsCartasDeBaralho(String id){
        return this.baralhos.get(id).getCartas();
    }

    public void embaralharBaralho(String id){
        this.baralhos.get(id).embaralharCartas();
    }

    public ArrayList<Carta> retirarNCartasDeBaralho(String id, int n){
        ArrayList<Carta> cartasRetiradas = this.baralhos.get(id).retirarNCartas(n);
        return cartasRetiradas;
    }

    public boolean excluirBaralho(String id){

        if(id != null){
            this.baralhos.remove(id);
            return true;
        }
        return false;
    }

    public HashMap<String, Baralho> getBaralhos() {
        return baralhos;
    }

}
