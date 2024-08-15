package engtelecom.std.api_baralho.service;

import engtelecom.std.api_baralho.entities.Baralho;

import java.util.*;

public class BaralhoService {

    private HashMap<String,Baralho> baralhos;

    public String criarBaralho(){
        String idBaralho = UUID.randomUUID().toString();
        Baralho novoBaralho = new Baralho(idBaralho);
        novoBaralho.iniciarCartas();
        this.baralhos.put(idBaralho, novoBaralho);
        return idBaralho;
    }

    public ArrayList<String> retornarTodosBaralhos(){
        ArrayList<String> idBaralhos = new ArrayList<>();
       
        for(String id: this.baralhos.keySet()){
            idBaralhos.add(id);
        }

        return idBaralhos;
    }

}
