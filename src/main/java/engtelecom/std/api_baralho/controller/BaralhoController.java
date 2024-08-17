package engtelecom.std.api_baralho.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import engtelecom.std.api_baralho.entities.Carta;
import engtelecom.std.api_baralho.service.BaralhoService;


@RestController

@RequestMapping({ "/baralhos", "/baralhos/" })
public class BaralhoController {
    @Autowired
    private BaralhoService baralhoService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<String> retornarBaralhos(){
        return this.baralhoService.retornarTodosBaralhos();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StringBuilder retornarTodasAsCartasDeBaralho (@PathVariable String id){

        StringBuilder jsonSeBaralhoNormal = this.formatarJsonCartas(this.baralhoService.retornarTodasAsCartasDeBaralho(id));
        StringBuilder jsonSeBaralhoEmbaralhado = new StringBuilder();

        jsonSeBaralhoEmbaralhado.append("\"Erro\":\"Baralho está embaralhado, não foi possível retornar as cartas");

        if(!(this.baralhoService.getBaralhos().get(id).isEstaEmbaralhado())){
            return jsonSeBaralhoNormal;
        }

        return jsonSeBaralhoEmbaralhado;
    }

    @GetMapping(value = "/carta/{carta}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String carta) {
        InputStream is = BaralhoController.class.getClassLoader().getResourceAsStream("static/cartas/" + carta);
        return ResponseEntity.ok().body(new InputStreamResource(is));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String criarBaralho(){
        return this.baralhoService.criarBaralho();
    }

    @DeleteMapping("/{id}")
    public String deletarBaralho(@PathVariable String id){

        if(this.baralhoService.excluirBaralho(id)){
            return HttpStatus.NO_CONTENT.toString();
        }
        return HttpStatus.NOT_FOUND.toString();
    }
    

    private StringBuilder formatarJsonCartas(ArrayList<Carta> listaCartas){
        StringBuilder json = new StringBuilder();
        json.append("{");

        // Adicionar a lista de cartas
        json.append("\"cartas\":[");
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

       for (Carta carta : listaCartas) {
            json.append("{")
            .append("\"valor\":\"").append(carta.obterValor()).append("\",")
            .append("\"naipe\":\"").append(carta.obterNaipe()).append("\",")
            .append("\"codigo\":\"").append(carta.getCodigo()).append("\",")
            .append("\"url\":\"").append(baseUrl).append("/cartas/").append(carta.getCodigo()).append(".png\"")
            .append("}").append(",");
       }

        json.deleteCharAt(json.length()-1);
        json.append("]");
        json.append("}");

        return json;
    }
}
