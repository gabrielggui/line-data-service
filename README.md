# line-data-service

Microserviço desenvolvido em Spring Boot.

## Dependências

* IDE recomendada: **Intellij**
* Java 21

### Configurações

Para clonar o projeto usando Git, execute:
```
git clone ssh://git@gitlab.redecorp.br:2222/framework-brasil/src/src-framework-brasil-archetype-ms.git
```

#### Windows

* Os arquivos "run.sh" e "mvnw" devem ser ajustados de CRLF para LF.

#### Linux

* No arquivo "run.sh" substituir **address=*:5005** por **address=0.0.0.0:5005** nos argumentos da JVM.

#### Maven
Na pasta do projeto, execute:
```
mvn clean install && mvn dependency:resolve
```

### Configuração para rodar sem o docker

* Para rodar a aplicação diretamente pelo SO, será necessário implantar os ENVs do session manager nas variaveis
  de ambiente do SO, esses localizados no arquivo **.env.example**

## Rodando a aplicação

### Pelo SO
* Garantir que o MongoDB esta em execução.
* Executar a aplicação via IDE.

### Pelo Docker
Na pasta do projeto, execute o seguinte comando para buildar a imagem:
```
docker compose build --no-cache framework-brasil-archetype-ms
```
Em seguida, execute o seguinte comando para startar a aplicação:
```
docker compose up
```

## Rotas

As rotas são definidas na camada de Controller definida pela anotação
@RestController, e processadas na camada de Service definida pela anotação @Service.

### Controller
```
@RestController
public class ExampleController {

    @Autowired
    private ExampleServiceImpl exampleService;
...
```

Nesta camada também são definidas as rotas com a através da anotação @PostMapping, segue exemplo:
```
    @PostMapping("/create")
    public ExampleDto createExample(@RequestBody ExampleDto exampleDto) {
        return this.exampleService.createExample(exampleDto);
    }
```
Lembrando que há uma anotação para cada operação REST, neste caso @PostMapping refere-se a HTTP POST.

### Service
No nosso caso, optamos por implementar uma Interface, porém a anotação vai somente
na implementação e nao na Interface.

Segue exemplo da Interface:
```
public interface ExampleService {

    ExampleDto createExample(ExampleDto exampleDto);
...
```

Segue exemplo do Service:
```
@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository repository;

    @Override
    public ExampleDto createExample(ExampleDto exampleDto) {
        Example example = new Example();
        example = repository.save(new Example(exampleDto));
        return new ExampleDto(example);
    }
```

### Acessando a Rota
Para acessar uma rota, deve-se considerar o **APPLICATION_PREFIX** definido no arquivo
".env.example" que neste caso é `/example/v1`

Logo, locamente a rota do exemplo ficaria:

`http://localhost:8080/example/v1/create`

## Testes
### Unitário
Testes unitários são implementados usando JUnit 5 (jupiter) e o Mockito.

Segue exemplo de implementação:
```
@ExtendWith(MockitoExtension.class)
public class ExampleControllerTests {
    @InjectMocks
    private ExampleController exampleController;

    @Mock
    private ExampleServiceImpl exampleService;

    @Test
    public void shouldDeleteExample() {
        Long exampleId = 1L;

        ResponseEntity<?> response = exampleController.deleteExample(exampleId);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(exampleService, times(1)).deleteExample(exampleId);
    }
```
Os testes unitários podem ser executados diretamente pela IDE ou pelo Maven através do seguinte comando:
``mvn test``

Segue exemplo de um report gerado pelo **jacoco**:
![JacocoReport](.gitlab/1-jacoco-report.jpg)

### Componente
Testes de componente são implementados usando Cucumber e JUnit.

Para o correto funcionamento do Cucumber uma classe de runner é necessária, segue exemplo:
```
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) 
@CucumberOptions(plugin = {"pretty"}, tags = "", features="src/test/resources/features")
public class CucumberRunner { }
```

Segue exemplo de um teste de componente utilizando o Cucumber:

Arquivo de features escrito em Gherkin (Linguagem do Cucumber):
```
  Scenario: Create example
    When the user sends a request to create the example
    Then the server should handle it and return "200" http status
```
Arquivo de Steps (Java):
```
    @When("the user sends a request to create the example")
    public void the_user_sends_a_request_to_create_the_example() {
		response = request.get(contextPath +"/create");
    }

    @Then("the server should handle it and return {string} http status")
    public void the_server_should_handle_it_and_return_a_success_http_status(String expectedAnswer) {
        assertEquals(expectedAnswer, Integer.toString(response.getStatusCode()));
    }
```
Os testes de componente podem ser executados diretamente pela IDE ou pelo Maven através do seguinte comando:
``mvn verify``

### Mutação
Testes de Mutação servem para avaliar a qualidade dos testes unitários, logo implementações serão melhorias
feitas nos próprios testes unitários.

No nosso projeto utilizamos a lib **pitest** para gerar este report, que também é utilizado na pipeline.

Para gerar o report, execute:

``mvn test-compile org.pitest:pitest-maven:mutationCoverage``


Segue exemplo de um report com 94% de cobertura:
![MutationReport](.gitlab/2-mutation-report.jpg)

Há também a possibilidade de adicionar o seguinte parametro para setar um threshold em porcentagem.

``-DmutationThreshold=90``

Desta maneira, caso o teste não tenha a cobertura de 90% o mesmo irá falhar.

## Lib de exceptions (Global Handler)
Segue link com documentação completa: https://gitlab.redecorp.br/framework-brasil/src/src-framework-brasil-exceptions-java-lib/blob/master/README.md

## Lib de Feature Flag (HobKnob)
Segue link com documentação completa: https://gitlab.redecorp.br/framework-brasil/src/src-framework-brasil-feature-toggle-java-lib/blob/master/README.md

## Swagger
O Swagger é gerado automaricamente pelo Swagger.io através da anotação
@ApiOperation na camada de Controller.

Segue exemplo:
```
    @ApiOperation(value = "Create a example")
    @PostMapping("/create")
    public ExampleDto createExample(@RequestBody ExampleDto exampleDto) {
        return this.exampleService.createExample(exampleDto);
    }
```

Para acessar o swagger do microserviço use o seguinte path `/example/v1/`

Exemplo local: `http://localhost:8080/example/v1/`

Segue exemplo:

![Swagger](.gitlab/3-swagger.jpg)

## Wiki

A documentação completa pode ser encontrada no link: https://wikicorp.telefonica.com.br/pages/viewpage.action?pageId=