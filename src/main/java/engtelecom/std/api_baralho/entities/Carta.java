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
    
    public String obterNaipe(){
        return this.naipe.getNaipe();
    }

    public String obterValor(){
        return this.getValor().getValor();
    }
}
