package engtelecom.std.api_baralho.entities;

public enum Valores {
    AS("As","1"),
    DOIS("2","2"),
    TRES("3","3"),
    QUATRO("4","4"),
    CINCO("5","5"),
    SEIS("6","6"),
    SETE("7","7"),
    OITO("8","8"),
    NOVE("9","9"),
    DEZ("10","10"),
    VALETE("Valete","j"),
    DAMA("Dama","q"),
    REI("Rei","k");

    
    public final String valor;
    public final String codigo;

    Valores(String valor, String codigo){
        this.valor = valor;
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public String getCodigo() {
        return codigo;
    }
    
    
}
