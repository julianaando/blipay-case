# Blipay Case 🪙

## Descrição

Foi desenvolvida uma API que realiza a análise de crédito de clientes para determinar a aprovação ou não de um empréstimo. As variáveis consideradas no cálculo do score de crédito incluem idade, renda mensal e a temperatura da cidade local.

## Funcionalidades principais

- **Análise de Score:** Recebe os dados do cliente e retorna se o empréstimo foi aprovado ou não com base no cálculo do score.
- **Consulta de Análises:** Permite consultar todas as análises de crédito realizadas por CPF.

## :woman_technologist: Tecnologias Utilizadas

- **Linguagem de Programação:** Java
- **Framework:** Spring
- **Banco de Dados:** H2
- **Testes:** JUnit
- **Arquitetura:** MVC e Hexagonal

## ⚙️ Como Executar

1. Clone o repositório em uma pasta de sua preferência
```
git clone git@github.com:julianaando/blipay-case.git
```
2. Entre na pasta que você acabou de clonar e instale as dependências
```
mvn clean install
```
3. Rode o projeto
```
mvn spring-boot:run
```

#### Para rodar os testes, entre no diretório **src/test/java/com/example/LoanApprovalSystem/service** e execute o comando 
```
mvn test
```

## 📚 Documentação (endpoints)

### :pencil: Leads

<details>
<summary> Análise de Score(POST) </summary>
  <br>

| Método | Funcionalidade | URL |
|---|---|---|
| `POST` | Cadastra um novo cliente e analisa seu score, para validar a liberação do empréstimo  | `http://localhost:8080/leads`

<details>
  <summary> A estrutura do corpo da requisição deve seguir o padrão abaixo: </summary>

  ```json
 {
    "name": "String",
    "age": "Integer",
    "cpf": "String",
    "monthlyIncome": "BigDecimal",
    "city": "String"
}
  ```
</details>

<details>
  <summary> Um exemplo de resposta bem-sucedida com <code>status 200</code> é: </summary>
  
  ```json
  {
    "Parabéns, o seu score é 232 e seu empréstimo foi aprovado."
}
```
</details>

:x:  A requisição irá falhar se algum dos atributos não for preenchido corretamente ou estiver ausente. O endpoint retornará um erro <code>400</code><br>

</details>

<details>
<summary> Consultas (GET) </summary>
  <br>
  
| Método | Funcionalidade | URL |
|---|---|---|
| `GET` | Consulta todas as análises de crédito realizadas por CPF | `http://localhost:8080/leads`

<details>
 <summary>  Um exemplo de resposta bem-sucedida com <code>status 200</code> é: </summary>
 
```json
[
  {
    "cpf": "012345678900",
    "score": 232,
    "createdAt": "2024-06-12T19:30:28.788+00:00"
  },
  // "Outras análises..."
]
```
</details>

:x:  A requisição irá falhar se não houver nenhuma análise de crédito cadastrada para o CPF. O endpoint retornará um erro <code>404</code><br>
<br>

</details>
