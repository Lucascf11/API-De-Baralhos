package engtelecom.std.api_baralho.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

/**
 * Representa uma carta de baralho com um código, um naipe e um valor.
 * 
 * Esta classe usa Lombok para gerar automaticamente métodos comuns como getters, setters, e toString.
 * Cada carta possui um código único, um naipe (como Copas, Espadas, etc.) e um valor (como As, 2, 3, etc.).
 */

@Data
@AllArgsConstructor
public class Carta {

     /**
     * Código alfanumérico da carta, que pode representar o valor e o naipe em uma forma compacta.
     * Este campo não pode ser nulo.
     */
    @NonNull
    private String codigo;

     /**
     * Naipe da carta (ex: Copas, Espadas). Este campo não pode ser nulo.
     */
    @NonNull
    private final Naipes naipe;
    
    /**
     * Valor da carta (ex: As, 2, 3). Este campo não pode ser nulo.
     */
    @NonNull
    private final Valores valor;
    
    /**
     * Obtém o nome textual do naipe da carta.
     * 
     * Este método retorna a representação textual do naipe em vez da enumeração {@link Naipes}.
     *
     * @return O nome do naipe da carta.
     */
    public String obterNaipe(){
        return this.naipe.getNaipe();
    }

    /**
     * Obtém o valor textual da carta.
     * 
     * Este método retorna a representação textual do valor da carta em vez da enumeração {@link Valores}.
     *
     * @return O valor da carta.
     */
    public String obterValor(){
        return this.getValor().getValor();
    }
}
