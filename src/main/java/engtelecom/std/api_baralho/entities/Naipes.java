package engtelecom.std.api_baralho.entities;


public enum Naipes {
    COPAS("copas",0),
    ESPADAS("espadas",1), 
    OUROS("ouros",2),
    PAUS("paus",3);

    public final int codigo;
    public final String naipe;

    Naipes(String naipe, int codigo){
        this.naipe = naipe;
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNaipe() {
        return naipe;
    }

    
}
