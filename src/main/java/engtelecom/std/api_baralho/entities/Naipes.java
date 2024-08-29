package engtelecom.std.api_baralho.entities;


/**
 * Enumeração que representa os naipes de um baralho de cartas.
 * 
 * Esta enumeração define quatro naipes: Copas, Espadas, Ouros e Paus.
 * Cada naipe é associado a um código numérico e uma string que o representa.
 * 
 * Os naipes são utilizados em jogos de cartas para distinguir os diferentes 
 * tipos de cartas presentes em um baralho.
 */

public enum Naipes {
    /** Naipe de Copas, com código 0. */
    COPAS("copas",0),
    /** Naipe de Espadas, com código 1. */
    ESPADAS("espadas",1), 
    /** Naipe de Ouros, com código 2. */
    OUROS("ouros",2),
     /** Naipe de Paus, com código 3. */
    PAUS("paus",3);

    /** Código associado ao naipe. */
    public final int codigo;
    /** Nome do naipe. */
    public final String naipe;
    

    /**
     * Construtor para inicializar um naipe com o nome e código fornecidos.
     *
     * @param naipe O nome do naipe.
     * @param codigo O código numérico associado ao naipe.
     */
    Naipes(String naipe, int codigo){
        this.naipe = naipe;
        this.codigo = codigo;
    }

    /**
     * Obtém o código numérico do naipe.
     *
     * @return O código numérico do naipe.
     */
    public int getCodigo() {
        return codigo;
    }

    /**
     * Obtém o nome do naipe.
     *
     * @return O nome do naipe.
     */
    public String getNaipe() {
        return naipe;
    }

    
}
