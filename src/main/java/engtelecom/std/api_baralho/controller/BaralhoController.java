package engtelecom.std.api_baralho.controller;

import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/baralhos", "/baralhos/" })
public class BaralhoController {
    @GetMapping(value = "/carta/{carta}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String carta) {
        InputStream is = BaralhoController.class.getClassLoader().getResourceAsStream("static/cartas/" + carta);
        return ResponseEntity.ok().body(new InputStreamResource(is));
    }
}
