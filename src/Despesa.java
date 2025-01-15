import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Despesa {
    private String descricao;
    private double valor;
    private String categoria;

    private static List<Despesa> despesas = new ArrayList<>();

    public Despesa(String descricao, double valor, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.categoria = categoria;
    }


    @Override
    public String toString() {
        return "Descrição: " + descricao + ", Valor: R$ " + valor + ", Categoria: " + categoria;
    }

    public static void adicionarDespesa(String descricao, double valor, String categoria) {
        Scanner despesa = new Scanner(System.in);
        System.out.println("-----Adicionar Despesa-----");
        System.out.println("Descrição:");
        descricao = despesa.next();


        System.out.println("Valor:");
        valor = despesa.nextDouble();


        System.out.println("Categoria:");
        categoria = despesa.next();

        System.out.println("Despesa adicionada com sucesso!");
        Despesa novaDespesa = new Despesa(descricao, valor, categoria);
        despesas.add(novaDespesa);


    }

    public static void deletarDespesas(){
        Scanner scanner = new Scanner(System.in);
        listarDespesas();
        System.out.println("Qual despesa deseja deletar pela categoria:");
        String categoriaParaDeletar = scanner.nextLine();

        despesas.removeIf(despesa -> despesa.getCategoria().equalsIgnoreCase(categoriaParaDeletar));
        System.out.println("Despesas da categoria '" + categoriaParaDeletar + "' foram deletadas.");



    }
    public static void listarDespesas(){
        System.out.println("----- Lista de Despesas -----");
        for (Despesa despesa : despesas) {
            System.out.println(despesa);
        }
    }
    public static void calcularTotalDespesas(){
        double total = 0;
        for (Despesa despesa : despesas) {
            total += despesa.getValor();
        }
        System.out.println("Total das Despesas: R$ " + total);

    }
    public static void salvarDespesas(String nomeArquivo){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo))){
            for (Despesa despesa:despesas){
                writer.write(despesa.getDescricao()+ "," + despesa.getValor()+","+ despesa.getCategoria());
                writer.newLine();
            }
            System.out.println("Despesas salvas com sucesso no arquivo: " + nomeArquivo);
        }catch (IOException e){
            System.out.println("Erro ao salvar as despesas: " + e.getMessage());
        }


    }

    public static void carregarDespesas(String nomeArquivo){
        try (BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo))){
            String linha;
            while ((linha = reader.readLine()) != null){
                String[] partes = linha.split(",");
                String descricao = partes[0];
                double valor = Double.parseDouble(partes[1]);
                String categoria = partes[2];
                Despesa despesa = new Despesa(descricao,valor,categoria);
                despesas.add(despesa);
            }
            System.out.println("Despesas carregadas com sucesso do arquivo: " + nomeArquivo);
        }catch (IOException e){
            System.err.println("Erro ao carregar as despesas: " + e.getMessage());
        }


    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {

            System.out.println("----- Menu Principal -----");
            System.out.println("1. Adicionar Despesa");
            System.out.println("2. Listar Despesas");
            System.out.println("3. Deletar despesas");
            System.out.println("4. Calcular Total das Despesas");
            System.out.println("5. Salvar Despesas");
            System.out.println("6. Carregar Despesas");
            System.out.println("7. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1:
                    adicionarDespesa("",0,"");
                    break;
                case 2:
                    listarDespesas();
                    break;
                case 3:
                    deletarDespesas();
                   break;
                case 4:
                    calcularTotalDespesas();
                    break;
                case 5:
                    System.out.println("Digite o nome que deseja salvar o arquivo:");
                    String nomedoarquivo = scanner.nextLine();
                    salvarDespesas(nomedoarquivo);
                    break;
                case 6:
                    System.out.println("Digite o nome do arquivo que deseja carregar:");
                    String nomecarregar = scanner.nextLine();
                    carregarDespesas(nomecarregar);
                    break;
                case 7:
                    System.out.println("Saindo!!!!!!!!!11");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 7);

        scanner.close();
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public static List<Despesa> getDespesas() {
        return despesas;
    }

    public static void setDespesas(List<Despesa> despesas) {
        Despesa.despesas = despesas;
    }
}


