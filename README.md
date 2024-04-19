# line-data-service

Microserviço desenvolvido em Spring Boot.

## Dependências

* IDE recomendada: **Intellij IDEA**
* Java 21 LTS

### Configurações

Para clonar o projeto usando Git, execute:
```
git clone https://github.com/gabrielggui/line-data-service.git
```

#### Maven
Na pasta do projeto, execute:
```
mvn clean install && mvn dependency:resolve
```

## Rodando a aplicação

* Executar a aplicação via IDE.

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

Nesta camada também são definidas as rotas com a através da anotação @GetMapping, segue exemplo:
```
    @GetMapping("/find")
    public ExampleDto findExample(@RequestBody ExampleDto exampleDto) {
        return this.exampleService.findExample(exampleDto);
    }
```
Lembrando que há uma anotação para cada operação REST, neste caso @GetMapping refere-se a HTTP GET.

### Service
Foi optado por implementar uma Interface, com a anotação @Service inserida
na implementação.

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

## Swagger
O Swagger é gerado automaticamente pelo Swagger.io através da anotação
@Operation na camada de Controller.

Segue exemplo:
```
    @Operation(summary = "Create a example")
    @PostMapping("/create")
    public ExampleDto createExample(@RequestBody ExampleDto exampleDto) {
        return this.exampleService.createExample(exampleDto);
    }
```

Para acessar o swagger do microserviço, use o seguinte path `/swagger-ui/index.html`

Exemplo local: `http://localhost:8080/swagger-ui/index.html`

## Wiki

A documentação completa pode ser encontrada no link: https://wikicorp.telefonica.com.br/pages/viewpage.action?pageId=