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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import engtelecom.std.api_baralho.entities.Carta;
import engtelecom.std.api_baralho.service.BaralhoService;

/**
 * Controlador para gerenciar operações relacionadas a baralhos de cartas.
 * 
 * Esta classe expõe endpoints REST para criar, acessar, embaralhar, retirar cartas e excluir baralhos,
 * além de fornecer imagens das cartas.
 */
@RestController

@RequestMapping({ "/baralhos", "/baralhos/" })
public class BaralhoController {
    @Autowired
    private BaralhoService baralhoService;

    /**
     * Retorna todos os identificadores de baralhos existentes.
     * 
     * @return Um conjunto contendo todos os identificadores de baralhos.
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Set<String> retornarBaralhos(){
        return this.baralhoService.retornarTodosBaralhos();
    }

    /**
     * Retorna todas as cartas de um baralho específico. Se o baralho estiver embaralhado,
     * retorna uma mensagem de erro indicando que as cartas não podem ser listadas.
     * 
     * @param id O identificador do baralho.
     * @return Um JSON contendo todas as cartas do baralho ou uma mensagem de erro se o baralho estiver embaralhado.
     */
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StringBuilder retornarTodasAsCartasDeBaralho (@PathVariable String id){
        // Se o ID do baralho não existir, retornar que não foi encontrado
        if(!(this.baralhoService.getBaralhos().containsKey(id))){
            return this.jsonBaralhoNaoEncontrado();
        }
        
        // Esse StringBuilder retorna todas as cartas em formato JSON
        StringBuilder jsonSeBaralhoNormal = this.formatarJsonCartas(this.baralhoService.retornarTodasAsCartasDeBaralho(id));
        // Esse StringBuilder servirá para retornar que o baralho está embaralhado e as cartas não podem ser obtidas todas de uma vez
        StringBuilder jsonSeBaralhoEmbaralhado = new StringBuilder();
        
        // Se o baralho não está embaralhado, retorna todas as cartas
        if(!(this.baralhoService.getBaralhos().get(id).isEstaEmbaralhado())){
            return jsonSeBaralhoNormal;
        }

        // Se o baralho está embaralhado, retorna um JSON informando que não podem ser obtidas por esse motivo
        jsonSeBaralhoEmbaralhado.append("{").append("\"Erro\":\"Baralho embaralhado, cartas nao podem ser listadas\"").append("}");
        return jsonSeBaralhoEmbaralhado;
    }

    /**
     * Retorna a imagem de uma carta específica.
     * 
     * @param carta O código da carta cuja imagem será retornada.
     * @return Um {@link ResponseEntity} contendo a imagem da carta.
     */
    @GetMapping(value = "/carta/{carta}", produces = MediaType.IMAGE_PNG_VALUE)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getImage(@PathVariable String carta) {
        InputStream is = BaralhoController.class.getClassLoader().getResourceAsStream("static/cartas/" + carta);
        return ResponseEntity.ok().body(new InputStreamResource(is));
    }

    /**
     * Cria um novo baralho e retorna seu identificador.
     * 
     * @return O identificador do novo baralho.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String criarBaralho(){
        return this.baralhoService.criarBaralho();
    }

    /**
     * Embaralha as cartas de um baralho específico e retorna um JSON informando o sucesso da operação e o número de cartas restantes.
     * 
     * @param id O identificador do baralho a ser embaralhado.
     * @return Um JSON contendo informações sobre o sucesso da operação e o número de cartas restantes.
     */
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StringBuilder embaralharCartas(@PathVariable String id){

        StringBuilder jsonEmbaralhou = new StringBuilder();

        // Se o ID do baralho não existir, retornar que não foi encontrado
        if(!(this.baralhoService.getBaralhos().containsKey(id))){
            return this.jsonBaralhoNaoEncontrado();
        }
        
        // Embaralha as cartas e retorna um JSON informando que a operação foi bem sucedida e o número de cartas restantes nesse baralho
        this.baralhoService.embaralharBaralho(id);
        jsonEmbaralhou.append("{").append("\"Operacao\":\"Sucesso\",").
        append("\"CartasRestantes\":\"").append(this.baralhoService.obterCartasRestantes(id))
        .append("\"}");
        return jsonEmbaralhou;
    }
    
    /**
     * Retira um número específico de cartas de um baralho e retorna um JSON com as cartas retiradas e o número de cartas restantes.
     * 
     * @param id O identificador do baralho.
     * @param n O número de cartas a serem retiradas.
     * @return Um JSON contendo as cartas retiradas e o número de cartas restantes ou uma mensagem de erro.
     */
    @PutMapping(value = "/{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public StringBuilder retirarNCartasBaralho(@PathVariable String id, @RequestBody String n){
        // Converte-se n para um Integer, dessa maneira, fica mais palpável de realizar operações, como retirar n cartas de um baralho, por exemplo
        Integer numeroCartas = Integer.parseInt(n);

        StringBuilder jsonRetorno = new StringBuilder();
        // Se o ID do baralho não existir, retornar que não foi encontrado
        if(!this.baralhoService.getBaralhos().containsKey(id)){
            return this.jsonBaralhoNaoEncontrado();
        }

        // Se o numero de cartas for nulo, retorna um JSON dizendo que o corpo da requisição não pode ser nulo
        if(n.isEmpty() || numeroCartas == null){
            jsonRetorno.append("{").append("\"Erro\":\"O corpo da requisicao nao pode estar nulo\"").append("}");
            return jsonRetorno;
        }
        // Retornamos todas as cartas do baralho onde se quer realizar a operação e fazemos duas verificações antes de coletar cartas e retornar o JSON para o cliente:
        // 1º Analisa se o baralho está vazio e, se estiver, retorna um Json informando isso
        // 2° Analisa se o número de cartas que se quer tirar desse baralho é maior que o número de cartas que está no baralho e, caso isso venha a se consumar, retorna um erro em formato JSON informando isso
        // 3° Caso passe nas duas condições anteriores, coleta a quantidade de cartas e retorna para o usuário todas listadas em formato JSON, junto com a quantidade ainda restante no baralho
        if(this.baralhoService.retornarTodasAsCartasDeBaralho(id).isEmpty()){
            jsonRetorno.append("{").append("\"Erro\":\"Baralho vazio\"").append("}");
            return jsonRetorno;
        }else if(this.baralhoService.obterCartasRestantes(id) < numeroCartas){
            jsonRetorno.append("{").append("\"Erro\":\"Esta tentando obter mais cartas do que o restante no baralho\"").append("}");
            return jsonRetorno;
        }else{
            ArrayList<Carta> cartasRetiradas = this.baralhoService.retirarNCartasDeBaralho(id, numeroCartas);
            jsonRetorno.append(formatarJsonCartas(cartasRetiradas))
            .deleteCharAt(jsonRetorno.length() - 1).append(",") // Aqui estamos somente delentando a última chave do formatador de JSON para a ArrayList de cartas para adicionarmos os campos de cartas restantes
            .append("\"CartasRestantes\":\"").append(this.baralhoService.obterCartasRestantes(id))
            .append("\"}");
        }

        return jsonRetorno;
    }

    /**
     * Exclui um baralho da coleção de baralhos.
     * 
     * @param id O identificador do baralho a ser excluído.
     * @return Um status HTTP indicando o resultado da operação.
     */
    @DeleteMapping("/{id}")
    public String deletarBaralho(@PathVariable String id){
        // Se o ID do baralho existir, retornar 204 NO_CONTENT, caso contrário, retornar 404 NOT_FOUNDED
        if(this.baralhoService.excluirBaralho(id)){
            return HttpStatus.NO_CONTENT.toString();
        }
        return HttpStatus.NOT_FOUND.toString();
    }
    
   /**
     * Formata uma lista de cartas em JSON.
     * 
     * @param listaCartas A lista de cartas a ser formatada.
     * @return Um {@link StringBuilder} contendo o JSON das cartas.
     */
    private StringBuilder formatarJsonCartas(ArrayList<Carta> listaCartas){
        StringBuilder json = new StringBuilder();
        json.append("{");

        // Adicionar a lista de cartas
        json.append("\"cartas\":[");
        String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

       // Formatando Json com base na lista de cartas recebidas. O JSON está sendo montado de maneira braçal com o StringBuilder
       for (Carta carta : listaCartas) {
            json.append("{")
            .append("\"valor\":\"").append(carta.obterValor()).append("\",")
            .append("\"naipe\":\"").append(carta.obterNaipe()).append("\",")
            .append("\"codigo\":\"").append(carta.getCodigo()).append("\",")
            .append("\"url\":\"").append(baseUrl).append("/cartas/").append(carta.getCodigo()).append(".png\"")
            .append("}").append(",");
       }

        // Deletando a vírgula presente após a chave da última carta e fechando o vetor Json
        json.deleteCharAt(json.length()-1);
        json.append("]");
        json.append("}");

        return json;

    }

    /**
     * Cria um JSON indicando que o baralho não foi encontrado.
     * 
     * @return Um {@link StringBuilder} contendo o JSON de erro.
     */
    private StringBuilder jsonBaralhoNaoEncontrado(){
        StringBuilder jsonNaoEncontrou = new StringBuilder();
        jsonNaoEncontrou.append("{")
        .append("\"Erro\":\"Baralho nao encontrado\",")
        .append("\"Status\":").append(HttpStatus.NOT_FOUND.value())
        .append("}");
        return jsonNaoEncontrou;
    }

}
