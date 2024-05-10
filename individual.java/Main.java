import java.util.ArrayList;
import java.util.Scanner;

interface Trabalhavel {
    void trabalhar();
    void relatarProgresso();
}

abstract class Funcionario implements Trabalhavel {
    private String nome;
    private int matricula;

    public Funcionario(String nome, int matricula) {
        this.nome = nome;
        this.matricula = matricula;
    }

    public abstract double calcularSalario();

    public String getNome() {
        return nome;
    }

    public int getMatricula() {
        return matricula;
    }
}

class Gerente extends Funcionario {
    private double bonusAnual;
    private String equipe;

    public Gerente(String nome, int matricula, double bonusAnual, String equipe) {
        super(nome, matricula);
        this.bonusAnual = bonusAnual;
        this.equipe = equipe;
    }

    @Override
    public double calcularSalario() {
        // Implemente o cálculo do salário do gerente aqui
        return 0.0;
    }

    @Override
    public void trabalhar() {
        System.out.println("Gerente " + getNome() + " está gerenciando a equipe " + equipe);
    }

    @Override
    public void relatarProgresso() {
        System.out.println("Gerente " + getNome() + " está relatando o progresso da equipe " + equipe);
    }
}

class Desenvolvedor extends Funcionario {
    private ArrayList<String> tecnologias;

    public Desenvolvedor(String nome, int matricula, ArrayList<String> tecnologias) {
        super(nome, matricula);
        this.tecnologias = tecnologias;
    }

    @Override
    public double calcularSalario() {
        // Implemente o cálculo do salário do desenvolvedor aqui
        return 0.0;
    }

    @Override
    public void trabalhar() {
        System.out.println("Desenvolvedor " + getNome() + " está desenvolvendo utilizando as seguintes tecnologias: " + tecnologias);
    }

    @Override
    public void relatarProgresso() {
        System.out.println("Desenvolvedor " + getNome() + " está relatando o progresso do desenvolvimento");
    }
}

class Estagiario extends Funcionario {
    private int horasTrabalhadas;
    private String supervisor;

    public Estagiario(String nome, int matricula, int horasTrabalhadas, String supervisor) {
        super(nome, matricula);
        this.horasTrabalhadas = horasTrabalhadas;
        this.supervisor = supervisor;
    }

    @Override
    public double calcularSalario() {
        // Implemente o cálculo do salário do estagiário aqui
        return 0.0;
    }

    @Override
    public void trabalhar() {
        System.out.println("Estagiário " + getNome() + " está trabalhando sob supervisão de " + supervisor);
    }

    @Override
    public void relatarProgresso() {
        System.out.println("Estagiário " + getNome() + " está relatando o progresso do trabalho");
    }
}

public class Main {
    private static ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            executarOpcao(opcao);
        } while (opcao != 0);
        scanner.close();
    }

    private static void exibirMenu() {
        System.out.println("\nMenu");
        System.out.println("1. Adicionar funcionário");
        System.out.println("2. Remover funcionário");
        System.out.println("3. Listar funcionários");
        System.out.println("0. Sair");
    }

    private static void executarOpcao(int opcao) {
        switch (opcao) {
            case 1:
                adicionarFuncionario();
                break;
            case 2:
                removerFuncionario();
                break;
            case 3:
                listarFuncionarios();
                break;
            case 0:
                System.out.println("Saindo...");
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void adicionarFuncionario() {
        System.out.println("\nAdicionar funcionário:");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Matrícula: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        // Escolher o tipo de funcionário
        System.out.println("Escolha o tipo de funcionário:");
        System.out.println("1. Gerente");
        System.out.println("2. Desenvolvedor");
        System.out.println("3. Estagiário");
        System.out.print("Opção: ");
        int tipo = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner

        switch (tipo) {
            case 1:
                System.out.print("Bônus Anual: ");
                double bonusAnual = scanner.nextDouble();
                scanner.nextLine(); // Limpar o buffer do scanner
                System.out.print("Equipe: ");
                String equipe = scanner.nextLine();
                funcionarios.add(new Gerente(nome, matricula, bonusAnual, equipe));
                break;
            case 2:
                ArrayList<String> tecnologias = new ArrayList<>();
                while (true) {
                    System.out.print("Tecnologia (ou deixe em branco para parar): ");
                    String tecnologia = scanner.nextLine();
                    if (tecnologia.isEmpty()) {
                        break;
                    }
                    tecnologias.add(tecnologia);
                }
                funcionarios.add(new Desenvolvedor(nome, matricula, tecnologias));
                break;
            case 3:
                System.out.print("Horas Trabalhadas: ");
                int horasTrabalhadas = scanner.nextInt();
                scanner.nextLine(); // Limpar o buffer do scanner
                System.out.print("Supervisor: ");
                String supervisor = scanner.nextLine();
                funcionarios.add(new Estagiario(nome, matricula, horasTrabalhadas, supervisor));
                break;
            default:
                System.out.println("Opção inválida!");
                break;
        }
    }

    private static void removerFuncionario() {
        System.out.println("\nRemover funcionário:");
        System.out.print("Matrícula do funcionário a ser removido: ");
        int matricula = scanner.nextInt();
        scanner.nextLine(); // Limpar o buffer do scanner
        Funcionario funcionarioRemover = null;
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula() == matricula) {
                funcionarioRemover = funcionario;
                break;
            }
        }
        if (funcionarioRemover != null) {
            funcionarios.remove(funcionarioRemover);
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("Funcionário não encontrado!");
        }
    }

    private static void listarFuncionarios() {
        System.out.println("\nLista de Funcionários:");
        if (funcionarios.isEmpty()) {
            System.out.println("Nenhum funcionário cadastrado.");
        } else {
            for (Funcionario funcionario : funcionarios) {
                System.out.println("Nome: " + funcionario.getNome() + ", Matrícula: " + funcionario.getMatricula());
            }
        }
    }
}
