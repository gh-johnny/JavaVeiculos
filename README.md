# ⚙️ Análise Detalhada do Projeto: Calculadora de Custos de Aluguel de Veículos

Este projeto em Java implementa uma calculadora de custos de aluguel de veículos, focando em clareza, modularidade e testes rigorosos. A aplicação é estruturada em duas classes principais e uma interface, cada uma com responsabilidades bem definidas para garantir a manutenção e a extensibilidade do código.

## 🧩 Estrutura do Código

O projeto é composto pelos seguintes elementos:

1.  **`CostCalculator.java` (Interface):**
    * Define um contrato simples para qualquer classe que necessite calcular um custo baseado em um número de dias.
    * Possui um único método: `double calculate(int days)`.
    * Essa interface permite a implementação de diferentes estratégias de cálculo de custos no futuro, aderindo ao princípio de "aberto/fechado" do SOLID.

2.  **`Vehicle.java` (Classe):**
    * Representa um veículo disponível para aluguel.
    * **Atributos:**
        * `modelo` (String): O modelo do veículo. É `final`, indicando que não pode ser alterado após a criação do objeto.
        * `dailyRate` (double): A taxa de aluguel diária do veículo. É `final` e possui validação para garantir que seja um valor positivo.
        * **Constantes Estáticas:**
            * `DISCOUNT_THRESHOLD_DAYS` (int): O número mínimo de dias para que o desconto padrão seja aplicado (atualmente 7).
            * `REGULAR_DISCOUNT_RATE` (double): A taxa de desconto padrão para aluguéis acima do limite (atualmente 0.1 ou 10%).
            * `LATE_FEE_FACTOR` (double): O fator multiplicativo para calcular a taxa de multa por atraso (atualmente 0.02 ou 2% da taxa diária por dia de atraso).
    * **Construtor:**
        * `public Vehicle(String model, double dailyRate)`: Inicializa um novo objeto `Vehicle` com o modelo e a taxa diária fornecidos. Lança `IllegalArgumentException` se a `dailyRate` não for positiva.
    * **Métodos de Acesso (Getters):**
        * `public String getModel()`: Retorna o modelo do veículo.
        * `public double getDailyRate()`: Retorna a taxa diária de aluguel do veículo.
    * **`toString()` (Override):**
        * Retorna uma representação formatada do objeto `Vehicle`, incluindo o modelo e a taxa diária.
    * **`calculateRentalPrice(int rentalDays)`:**
        * Calcula o preço total do aluguel para um determinado número de dias.
        * Aplica o `REGULAR_DISCOUNT_RATE` se o `rentalDays` for maior ou igual a `DISCOUNT_THRESHOLD_DAYS`.
        * Lança `IllegalArgumentException` se `rentalDays` for negativo.
    * **`calculateLateReturnFee(int daysLate)`:**
        * Calcula a taxa de multa por atraso com base no número de dias de atraso.
        * A multa é calculada como `dailyRate * LATE_FEE_FACTOR * daysLate`.
        * Lança `IllegalArgumentException` se `daysLate` for negativo.

3.  **`Main.java` (Classe):**
    * Ponto de entrada principal da aplicação.
    * **`main(String[] args)`:**
        * Cria uma instância da classe `Vehicle`.
        * Chama métodos estáticos para exibir informações do veículo e analisar cenários de custos de aluguel.
    * **Métodos Estáticos:**
        * `private static void displayVehicleInfo(Vehicle vehicle)`: Exibe os detalhes do veículo formatados.
        * `private static void analyzeRentalScenarios(Vehicle vehicle)`: Calcula e exibe os custos para diferentes cenários de aluguel (padrão e estendido) e a taxa de multa por atraso, utilizando a formatação de moeda brasileira.
        * `private static void showProjection(String scenario, CostCalculator calculator, int days, NumberFormat formatter)`: Um método utilitário para exibir o resultado de um cálculo de custo específico, formatado com a moeda apropriada. Utiliza a interface `CostCalculator` para generalizar a exibição de diferentes tipos de custos.

## 🧪 Testes Unitários (`VehicleTest.java` e `MainTest.java`)

O projeto inclui testes unitários abrangentes para garantir a correção e a robustez do código:

* **`VehicleTest.java`:**
    * Testa o construtor da classe `Vehicle` com entradas válidas e inválidas.
    * Verifica o cálculo de `calculateRentalPrice` em cenários sem desconto, com desconto e com entrada inválida (dias negativos).
    * Testa o cálculo de `calculateLateReturnFee` com entrada válida e inválida (dias negativos).
    * Garante que o método `toString()` retorna a representação esperada do objeto `Vehicle`.

* **`MainTest.java`:**
    * Realiza um teste de integração leve, capturando a saída do método `main` e verificando se ela corresponde ao formato e aos valores esperados para os cenários de teste definidos. Isso garante que a interação entre as classes e a formatação da saída estão corretas.

## 💡 Pontos de Design e Decisões

* **Imutabilidade:** Os atributos `modelo` e `dailyRate` na classe `Vehicle` são `final`, promovendo a imutabilidade do objeto após a sua criação. Isso torna os objetos `Vehicle` mais seguros e previsíveis.
* **Validação de Entrada:** O construtor de `Vehicle` e os métodos de cálculo realizam validação de entrada para evitar estados inválidos e comportamentos inesperados. Exceções `IllegalArgumentException` são lançadas para sinalizar entradas problemáticas.
* **Separação de Responsabilidades:** A classe `Vehicle` é responsável pela lógica de representação do veículo e cálculo dos custos relacionados. A classe `Main` é responsável por demonstrar o uso da classe `Vehicle` e formatar a saída para o usuário.
* **Localização:** A classe `NumberFormat` com o `Locale` "pt", "BR" é utilizada para garantir que os valores monetários sejam exibidos no formato correto para o Brasil.
* **Uso de Interface:** A introdução da interface `CostCalculator` demonstra uma intenção de design para permitir a adição de diferentes estratégias de precificação ou outros tipos de custos no futuro, sem modificar significativamente a estrutura da classe `Main`.

## 🚀 Potenciais Melhorias e Expansões

* **Implementação de Descontos Progressivos:** A lógica de desconto progressivo para longo prazo poderia ser integrada diretamente na classe `Vehicle` ou através de implementações específicas da interface `CostCalculator`.
* **Adição de Outros Custos:** Poderiam ser adicionados outros custos, como taxas de seguro, taxas de serviço, etc., possivelmente implementando a interface `CostCalculator`.
* **Persistência de Dados:** Em uma aplicação mais completa, os dados dos veículos poderiam ser armazenados em um banco de dados ou arquivo.
* **Interface de Usuário:** Uma interface gráfica (GUI) ou uma interface de linha de comando (CLI) mais interativa poderia ser desenvolvida para permitir que os usuários insiram dados e visualizem os resultados de forma mais dinâmica.
* **Tratamento de Exceções Mais Granular:** Em cenários mais complexos, um tratamento de exceções mais específico poderia ser implementado para fornecer feedback mais detalhado.

Este projeto serve como uma base sólida para uma aplicação de cálculo de custos de aluguel de veículos, demonstrando boas práticas de programação orientada a objetos, tratamento de erros e testes unitários.
