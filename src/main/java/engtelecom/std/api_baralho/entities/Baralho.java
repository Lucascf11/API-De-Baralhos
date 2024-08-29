package engtelecom.std.api_baralho.entities;


import java.util.ArrayList;
import java.util.Collections;

/**
 * Representa um baralho de cartas.
 * 
 * Esta classe gerencia um baralho de cartas, incluindo a inicialização com todas as cartas, o embaralhamento das cartas e a retirada de um número específico de cartas.
 */
public class Baralho {

    private String id;

    private ArrayList<Carta> cartas;

    private boolean estaEmbaralhado;

    /**
     * Obtém o identificador do baralho.
     * 
     * @return O identificador do baralho.
     */
    public String getId() {
        return id;
    }

    /**
     * Obtém a lista de cartas do baralho.
     * 
     * @return A lista de cartas.
     */
    public ArrayList<Carta> getCartas() {
        return cartas;
    }

    /**
     * Verifica se o baralho está embaralhado.
     * 
     * @return {@code true} se o baralho estiver embaralhado; {@code false} caso contrário.
     */

    public boolean isEstaEmbaralhado() {
        return estaEmbaralhado;
    }

    /**
     * Define o estado de embaralhamento do baralho.
     * 
     * @param estaEmbaralhado {@code true} para marcar o baralho como embaralhado; {@code false} caso contrário.
     */
    public void setEstaEmbaralhado(boolean estaEmbaralhado) {
        this.estaEmbaralhado = estaEmbaralhado;
    }

    /**
     * Constrói um novo baralho com um identificador único.
     * 
     * @param id O identificador do baralho.
     */
    public Baralho(String id) {
        this.id = id;
        this.cartas = new ArrayList<>();
        this.estaEmbaralhado = false;
    }

    /**
     * Inicializa o baralho com todas as cartas possíveis.
     * 
     * Este método adiciona todas as combinações possíveis de naipes e valores ao baralho.
     */
    public void iniciarCartas(){

        for(Naipes naipe: Naipes.values()){

            String codigoNaipe = String.valueOf(naipe.naipe.charAt(0));

            for(Valores valor: Valores.values()){

                String codigoValor = valor.codigo;
                this.cartas.add(new Carta(codigoValor+codigoNaipe, naipe, valor));
            }
        }
    }

    /**
     * Embaralha as cartas do baralho.
     * 
     * Este método usa o método {@link Collections#shuffle(java.util.List)} para embaralhar a lista de cartas
     * e marca o baralho como embaralhado.
     */
    public void embaralharCartas(){
       Collections.shuffle(this.cartas);
       this.setEstaEmbaralhado(true);
    }

    /**
     * Retira um número específico de cartas do baralho.
     * 
     * @param n O número de cartas a serem retiradas.
     * @return Uma lista contendo as cartas retiradas.
     */
    public ArrayList<Carta> retirarNCartas(int n){

        ArrayList<Carta> cartasRetiradas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if(!(this.cartas.isEmpty())){
                cartasRetiradas.add(this.cartas.remove(0));
            }
        }
        return cartasRetiradas;
    }

}
