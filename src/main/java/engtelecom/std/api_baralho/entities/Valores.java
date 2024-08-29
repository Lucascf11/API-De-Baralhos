package engtelecom.std.api_baralho.entities;

/**
 * Enumeração que representa os valores das cartas em um baralho.
 * 
 * Esta enumeração define os valores das cartas como As, 2, 3, ..., 10, Valete, Dama e Rei.
 * Cada valor é associado a um código alfanumérico que pode ser usado em representações
 * compactas das cartas.
 */
public enum Valores {
    /** Valor As, com código "1". */
    AS("As","1"),
    /** Valor 2, com código "2". */
    DOIS("2","2"),
    /** Valor 3, com código "3". */
    TRES("3","3"),
    /** Valor 4, com código "4". */
    QUATRO("4","4"),
    /** Valor 5, com código "5". */
    CINCO("5","5"),
    /** Valor 6, com código "6". */
    SEIS("6","6"),
    /** Valor 7, com código "7". */
    SETE("7","7"),
    /** Valor 8, com código "8". */
    OITO("8","8"),
    /** Valor 9, com código "9". */
    NOVE("9","9"),
    /** Valor 10, com código "10". */
    DEZ("10","10"),
    /** Valor Valete, com código "j". */
    VALETE("Valete","j"
    /** Valor Dama, com código "q". */),
    DAMA("Dama","q"),
    /** Valor Rei, com código "k". */
    REI("Rei","k");

    /** Nome textual do valor da carta. */
    public final String valor;
    /** Código alfanumérico associado ao valor da carta. */
    public final String codigo;

    /**
     * Construtor para inicializar um valor de carta com o nome e código fornecidos.
     *
     * @param valor O nome textual do valor da carta.
     * @param codigo O código alfanumérico associado ao valor da carta.
     */
    Valores(String valor, String codigo){
        this.valor = valor;
        this.codigo = codigo;
    }

    /**
     * Obtém o nome textual do valor da carta.
     *
     * @return O nome textual do valor da carta.
     */
    public String getValor() {
        return valor;
    }

    /**
     * Obtém o código alfanumérico do valor da carta.
     *
     * @return O código alfanumérico do valor da carta.
     */
    public String getCodigo() {
        return codigo;
    }
    
    
}
