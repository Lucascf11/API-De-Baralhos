# Serviço WEB de Baralhos

# Índice
- [Serviço WEB de Baralhos](#serviço-web-de-baralhos)
- [Índice](#índice)
  - [Objetivo](#objetivo)
  - [Operações atendidas](#operações-atendidas)
  - [Requisitos atendidos](#requisitos-atendidos)
  - [Desenho da API](#desenho-da-api)
  - [Executando a API em sua máquina](#executando-a-api-em-sua-máquina)
  - [Consumindo a API com cURL](#consumindo-a-api-com-curl)
    - [Sintaxe dos métodos Curl adaptados para o projeto](#sintaxe-dos-métodos-curl-adaptados-para-o-projeto)
      - [Criando baralho com o método POST](#criando-baralho-com-o-método-post)
      - [Obtendo a lista de baralhos com o método GET](#obtendo-a-lista-de-baralhos-com-o-método-get)
      - [Obtendo todas as cartas de um baralho com o método GET](#obtendo-todas-as-cartas-de-um-baralho-com-o-método-get)
      - [Embaralhando um baralho com o método PUT](#embaralhando-um-baralho-com-o-método-put)
      - [Obtendo n cartas de um baralho com o método PUT](#obtendo-n-cartas-de-um-baralho-com-o-método-put)
      - [Deletando um baralho com o método DELETE](#deletando-um-baralho-com-o-método-delete)
    - [Exemplos de consumo dos recursos da API com cURL](#exemplos-de-consumo-dos-recursos-da-api-com-curl)
      - [Criando novos baralho através do POST](#criando-novos-baralho-através-do-post)
      - [Obtendo todos os baralhos através do GET](#obtendo-todos-os-baralhos-através-do-get)
      - [Obtendo todas as cartas de um baralho através do GET](#obtendo-todas-as-cartas-de-um-baralho-através-do-get)
      - [Embaralhando um baralho através do PUT](#embaralhando-um-baralho-através-do-put)
      - [Retirando n cartas de um baralho através do PUT](#retirando-n-cartas-de-um-baralho-através-do-put)
      - [Deletando um baralho através do DELETE](#deletando-um-baralho-através-do-delete)
  - [Acessando a documentação Open API do projeto](#acessando-a-documentação-open-api-do-projeto)

## Objetivo

Com base na aula sobre Serviços Web e no Laboratório 4 - Serviços Web REST em Java, o objetivo
desse projeto é desenvolver uma API RESTful para um baralho de cartas. A API deve ser desenvolvida
em Java com o uso do framework Spring Boot e deve permitir as seguintes operações:

1) Criar um novo baralho de cartas
2) Listar todas as cartas de um baralho novo
3) Embaralhar as cartas do baralho
4) Retirar n cartas do baralho
5) Excluir um baralho

* OBS: Informações mais detalhadas sobre cada operação nas [especificações do projeto](/docs/std-projeto-01.pdf)

## Operações atendidas 
 - [x] Criar um novo baralho de cartas
 - [x] Listar todas as cartas de um baralho novo
 - [x] Embaralhar as cartas do baralho
 - [x] Retirar n cartas do baralho
 - [x] Excluir um baralho

## Requisitos atendidos
- [ ] Indicar no arquivo Readme.md os requisitos atendidos e não atendidos
- [ ] Desenho da API RESTful em Java com a lógica correta para cada operação
- [ ] Implementação da API RESTful em JAva com a lógica correta para cada operação
- [ ] Documentação API RESTful com o uso do OpenAPI
- [ ] Arquivo Readme.md com instruções com o cURL para testar cada operação da API
- [ ] Arquivo .gitignore adequado para cada projeto Java com Gradle
- [ ] Após clonar o repositório, ser possível compilar e executar usando o gradle bootRun

## Desenho da API
![A imagem PNG do desenho da API](/images/Tabela_Projeto.png)
Você pode visualizar melhor o desenho da API [Neste PDF](/docs/Tabela_Projeto.pdf)

## Executando a API em sua máquina
1) Clone este repositório em máquina local
2) Entre na raíz do repositório e execute o projeto com o gradle bootRun ![Executando o projeto com gradle bootRun](/images/Executando_API.png)
3) Após isso, o seu computador passou a prover o serviço WEB de Baralhos aqui implementado e seus recursos estão prontos para serem consumidos

## Consumindo a API com cURL
Para realizarmos o consumo, primeiramente vamos às sintaxes cURL para cada método e recurso. Essa sintaxe foi concedida pelo Open API do próprio Spring Boot utilizado no presente projeto.

### Sintaxe dos métodos Curl adaptados para o projeto
Para a apresentação da Sintaxe e os exemplos de execução, posteriormente amostrados, iremos assumir que a aplicação está rodando na máquina local.

#### Criando baralho com o método POST
```bash
curl -X 'POST' \
  'http://localhost:8080/baralhos' \
  -H 'accept: */*' \
  -d ''
```

#### Obtendo a lista de baralhos com o método GET
```bash
curl -X 'GET' \
  'http://localhost:8080/baralhos/' \
  -H 'accept: */*'
```

#### Obtendo todas as cartas de um baralho com o método GET
```bash
curl -X 'GET' \
  'http://localhost:8080/baralhos/{ID}' \
  -H 'accept: */*'
```
* OBS: Substituir "{ID}" pelo ID do baralho que você quer realizar a operação

#### Embaralhando um baralho com o método PUT
```bash
curl -X 'PUT' \
  'http://localhost:8080/baralhos/{ID}' \
  -H 'accept: */*'
```
* OBS: Substituir "{ID}" pelo ID do baralho que você quer realizar a operação

#### Obtendo n cartas de um baralho com o método PUT
```bash
curl -X 'PUT' \
  'http://localhost:8080/baralhos/{ID}' \
  -H 'accept: */*' \
  -H 'Content-Type: text/plain' \
  -d '{n}'
```
* OBS: Substituir "{ID}" pelo ID do baralho que você quer realizar a operação
* OBS²: Substituir {n} pelo número de cartas que você quer tirar do baralho

#### Deletando um baralho com o método DELETE
```bash
curl -X 'DELETE' \
  'http://localhost:8080/baralhos/{ID}' \
  -H 'accept: */*'
```
* OBS: Substituir "{ID}" pelo ID do baralho que você quer realizar a operação

Com toda essa apresentação da sintaxe do cliente cURL adaptada para esse projeto, vamos listar um exemplo de uso para cada operação:

### Exemplos de consumo dos recursos da API com cURL
* OBS: O formator json.tool do python3 será utilizado aqui para comprovarmos que estamos obtendo respostas JSON.


#### Criando novos baralho através do POST
![Criando baralho com POST](/images/POST_Cria_Baralho.png)
#### Obtendo todos os baralhos através do GET
![Obtendo todos os baralhos já criados com GET](/images/GET_Obtem_Baralhos.png)
#### Obtendo todas as cartas de um baralho através do GET
![Obtendo todas as cartas de um baralho com GET, requisição](/images/GET_Lista_Todas_As_Cartas_Request.png)
![Obtendo todas as cartas de um baralho com GET, resposta](/images/GET_Lista_Todas_As_Cartas_Response.png)
#### Embaralhando um baralho através do PUT
![Embaralhando um baralho com PUT](/images/PUT_Embaralha_Baralho.png)
#### Retirando n cartas de um baralho através do PUT
![Retirando 4 cartas de um baralho com PUT](/images/PUT_Obtem_n_Cartas.png)
#### Deletando um baralho através do DELETE
![Deletando um baralho com DELETE](/images/DELETE_Deleta_Baralho.png)

## Acessando a documentação Open API do projeto
Após executar o projeto, você pode acessar a OpenAPI do projeto entrando na seguinte URL (considerando que você está na máquina local onde a palicação está sendo executada): http://localhost:8080/swagger-ui/index.html#/baralho-controller
![Acessando a OpenAPI do projeto](/images/Acessando_a_OpenAPI.png)

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/g-3PKEPq)
