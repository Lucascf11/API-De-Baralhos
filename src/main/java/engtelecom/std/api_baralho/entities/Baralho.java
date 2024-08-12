package engtelecom.std.api_baralho.entities;

import io.micrometer.common.lang.NonNull;
import lombok.Data;
import java.util.ArrayList;


@Data
public class Baralho {

    @NonNull
    private int id;

    private ArrayList<Carta> cartas = new ArrayList();
}
