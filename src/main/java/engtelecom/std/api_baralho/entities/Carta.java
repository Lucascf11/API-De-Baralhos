package engtelecom.std.api_baralho.entities;

import io.micrometer.common.lang.NonNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Carta {
    @NonNull
    private final Naipes naipe;
    @NonNull
    private final Valores valor;
}
