package engtelecom.std.api_baralho.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class Carta {
    
    @NonNull
    private String codigo;
    @NonNull
    private final Naipes naipe;
    @NonNull
    private final Valores valor;
    
    // Método get que força a carta a entregar o nome do Naipe em vez da ENUM
    public String obterNaipe(){
        return this.naipe.getNaipe();
    }

    // Método get que força a carta a entregar o valorda carta em vez da ENUM
    public String obterValor(){
        return this.getValor().getValor();
    }
}
