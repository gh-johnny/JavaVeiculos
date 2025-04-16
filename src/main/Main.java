import java.text.NumberFormat;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Veiculo meuCarro = new Veiculo("Hatch", 60.00);

        mostrarInformacoes(meuCarro);
        mostrarAnaliseCustos(meuCarro);
    }

    private static void mostrarInformacoes(Veiculo veiculo) {
        System.out.println("Detalhes do Veículo:");
        System.out.println(veiculo);
        System.out.println("----------");
    }

    private static void mostrarAnaliseCustos(Veiculo veiculo) {
        NumberFormat moedaBrasil = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        System.out.println("Análise de Custos de Locação:");
        System.out.printf("Locação padrão (5 dias): %s%n", moedaBrasil.format(veiculo.calcularCustoLocacao(5)));
        System.out.printf("Locação prolongada (10 dias): %s%n", moedaBrasil.format(veiculo.calcularCustoLocacao(10)));
        System.out.printf("Multa por atraso (3 dias): %s%n", moedaBrasil.format(veiculo.calcularCustoMulta(3)));
    }
}
