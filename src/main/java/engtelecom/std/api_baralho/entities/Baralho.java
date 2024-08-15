package engtelecom.std.api_baralho.entities;


import java.util.ArrayList;
import java.util.Collections;

public class Baralho {

    private String id;

    private ArrayList<Carta> cartas;

    private boolean estaEmbaralhado;

    public String getId() {
        return id;
    }

    public ArrayList<Carta> getCartas() {
        return cartas;
    }
    
    public boolean isEstaEmbaralhado() {
        return estaEmbaralhado;
    }

    public void setEstaEmbaralhado(boolean estaEmbaralhado) {
        this.estaEmbaralhado = estaEmbaralhado;
    }

    public Baralho(String id) {
        this.id = id;
        this.cartas = new ArrayList<>();
        this.estaEmbaralhado = false;
    }

    public void iniciarCartas(){

        for(Naipes naipe: Naipes.values()){

            String codigoNaipe = String.valueOf(naipe.naipe.charAt(0));

            for(Valores valor: Valores.values()){

                String codigoValor = valor.codigo;
                this.cartas.add(new Carta(codigoValor+codigoNaipe, naipe, valor));
                
            }
        }

        Carta cartaNova = this.cartas.get(0);
        cartaNova.getNaipe().getNaipe();
    }

    public void embaralharCartas(){
       Collections.shuffle(this.cartas);
       this.setEstaEmbaralhado(true);
    }

    public ArrayList<Carta> retirarCartas(int n){

        ArrayList<Carta> cartasRetiradas = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            if(!(this.cartas.isEmpty())){
                cartasRetiradas.add(this.cartas.get(0));
                this.cartas.remove(0);
            }
        }

        return cartasRetiradas;
    }

}
