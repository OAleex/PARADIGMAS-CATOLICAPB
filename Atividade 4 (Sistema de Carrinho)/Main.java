import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static class Item {
        int codigo;
        String descricao;
        double valor;
        double acrescimo;
        double desconto;

        Item(int codigo, String descricao, double valor) {
            this.codigo = codigo;
            this.descricao = descricao;
            this.valor = valor;
            this.acrescimo = 0;
            this.desconto = 0;
        }
    }

    static List<Item> carrinho = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            mostrarMenu();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    inserirItem(scanner);
                    break;
                case 2:
                    acrescimoItem(scanner);
                    break;
                case 3:
                    descontoItem(scanner);
                    break;
                case 4:
                    acrescimoTotal(scanner);
                    break;
                case 5:
                    descontoTotal(scanner);
                    break;
                case 6:
                    finalizarVenda();
                    break;
                case 0:
                    System.out.println("Saindo do sistema...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static void mostrarMenu() {
        System.out.println("\nMenu:");
        System.out.println("1 - Inserir item ao carrinho");
        System.out.println("2 - Acréscimo de item");
        System.out.println("3 - Desconto de item");
        System.out.println("4 - Acréscimo total");
        System.out.println("5 - Desconto total");
        System.out.println("6 - Finalizar venda");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void inserirItem(Scanner scanner) {
        System.out.print("Digite o código do item: ");
        int codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite a descrição do item: ");
        String descricao = scanner.nextLine();

        System.out.print("Digite o valor do item: ");
        double valor = scanner.nextDouble();

        Item item = new Item(codigo, descricao, valor);
        carrinho.add(item);
        System.out.println("Item inserido com sucesso!");
    }

    public static void acrescimoItem(Scanner scanner) {
        System.out.print("Digite o código do item para aplicar acréscimo: ");
        int codigo = scanner.nextInt();

        Item item = buscarItemPorCodigo(codigo);
        if (item != null) {
            System.out.print("Digite o valor do acréscimo: ");
            double valorAcrescimo = scanner.nextDouble();
            item.acrescimo += valorAcrescimo;
            item.valor += valorAcrescimo;
            System.out.println("Acréscimo aplicado com sucesso!");
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    public static void descontoItem(Scanner scanner) {
        System.out.print("Digite o código do item para aplicar desconto: ");
        int codigo = scanner.nextInt();

        Item item = buscarItemPorCodigo(codigo);
        if (item != null) {
            System.out.print("Digite o valor do desconto: ");
            double valorDesconto = scanner.nextDouble();
            item.desconto += valorDesconto;
            item.valor -= valorDesconto;
            System.out.println("Desconto aplicado com sucesso!");
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    public static void acrescimoTotal(Scanner scanner) {
        System.out.print("Digite o valor total do acréscimo: ");
        double valorAcrescimo = scanner.nextDouble();
        distribuirAcrescimo(valorAcrescimo);
        System.out.println("Acréscimo total aplicado com sucesso!");
    }

    public static void descontoTotal(Scanner scanner) {
        System.out.print("Digite o valor total do desconto: ");
        double valorDesconto = scanner.nextDouble();
        distribuirDesconto(valorDesconto);
        System.out.println("Desconto total aplicado com sucesso!");
    }

    public static void finalizarVenda() {
        double descontoTotal = calcularDescontoTotal();
        double acrescimoTotal = calcularAcrescimoTotal();
        double valorTotal = calcularValorTotal();

        System.out.println("\nItens do Carrinho:");
        for (Item item : carrinho) {
            System.out.println("Código: " + item.codigo +
                    ", Descrição: " + item.descricao +
                    ", Valor: " + item.valor +
                    ", Acréscimo: " + item.acrescimo +
                    ", Desconto: " + item.desconto +
                    ", Total: " + item.valor);
        }

        System.out.println("\nDesconto Total: " + descontoTotal);
        System.out.println("Acréscimo Total: " + acrescimoTotal);
        System.out.println("Valor Total: " + valorTotal);
    }

    public static Item buscarItemPorCodigo(int codigo) {
        for (Item item : carrinho) {
            if (item.codigo == codigo) {
                return item;
            }
        }
        return null;
    }

    public static void distribuirAcrescimo(double valorAcrescimo) {
        int numItens = carrinho.size();
        if (numItens > 0) {
            double acrescimoPorItem = valorAcrescimo / numItens;
            for (Item item : carrinho) {
                item.acrescimo += acrescimoPorItem;
                item.valor += acrescimoPorItem;
            }
        }
    }

    public static void distribuirDesconto(double valorDesconto) {
        int numItens = carrinho.size();
        if (numItens > 0) {
            double descontoPorItem = valorDesconto / numItens;
            for (Item item : carrinho) {
                item.desconto += descontoPorItem;
                item.valor -= descontoPorItem;
            }
        }
    }

    public static double calcularDescontoTotal() {
        double total = 0;
        for (Item item : carrinho) {
            total += item.desconto;
        }
        return total;
    }

    public static double calcularAcrescimoTotal() {
        double total = 0;
        for (Item item : carrinho) {
            total += item.acrescimo;
        }
        return total;
    }

    public static double calcularValorTotal() {
        double total = 0;
        for (Item item : carrinho) {
            total += item.valor;
        }
        return total;
    }
}
