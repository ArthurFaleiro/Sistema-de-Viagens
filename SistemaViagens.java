import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaViagens {
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Viagem> viagens = new ArrayList<>();
    private static ArrayList<Atividade> atividades = new ArrayList<>();
    private static ArrayList<Acomodacao> acomodacoes = new ArrayList<>();
    
    private static Scanner scanner = new Scanner(System.in);
    private static int idCounterUser = 1;
    private static int idCounterViagem = 1;
    private static int idCounterAtiv = 1;
    private static int idCounterAcom = 1;

    public static void main(String[] args) {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n=== SISTEMA DE PLANEJAMENTO DE VIAGENS ===");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Cadastrar Viagem");
            System.out.println("3. Adicionar Atividade");
            System.out.println("4. Adicionar Acomodação");
            System.out.println("5. Consultar Itinerário");
            System.out.println("6. Calcular Orçamento Total");
            System.out.println("7. Consultar Viagens por Usuário");
            System.out.println("8. [INOVAÇÃO] Gerenciar Bagagem (Checklist)");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um número.");
                continue;
            }

            switch (opcao) {
                case 1: cadastrarUsuario(); break;
                case 2: cadastrarViagem(); break;
                case 3: adicionarAtividade(); break;
                case 4: adicionarAcomodacao(); break;
                case 5: consultarItinerario(); break;
                case 6: calcularOrcamentoTotal(); break;
                case 7: consultarViagensPorUsuario(); break;
                case 8: gerenciarBagagem(); break;
                case 0: System.out.println("Encerrando o sistema..."); break;
                default: System.out.println("Opção inválida!");
            }
        }
    }

    // --- MÉTODOS DO MENU ---
    private static void cadastrarUsuario() {
        System.out.print("Nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                System.out.println("ERRO: Email já cadastrado!");
                return;
            }
        }

        Usuario u = new Usuario(idCounterUser++, nome, email);
        usuarios.add(u);
        System.out.println("Usuário cadastrado com sucesso! ID: " + u.getId());
    }

    private static void cadastrarViagem() {
        System.out.print("ID do Usuário responsável: ");
        int idUser = Integer.parseInt(scanner.nextLine());
        Usuario responsavel = null;
        for (Usuario u : usuarios) {
            if (u.getId() == idUser) responsavel = u;
        }

        if (responsavel == null) {
            System.out.println("Usuário não encontrado.");
            return;
        }

        System.out.print("Destino: ");
        String destino = scanner.nextLine();
        System.out.print("Data Início (dd/MM/yyyy): ");
        String dataIni = scanner.nextLine();
        System.out.print("Data Fim (dd/MM/yyyy): ");
        String dataFim = scanner.nextLine();

        if (!validarDatas(dataIni, dataFim)) {
            System.out.println("ERRO: A data de fim deve ser posterior à data de início.");
            return;
        }

        Viagem v = new Viagem(idCounterViagem++, destino, dataIni, dataFim, responsavel);
        viagens.add(v);
        System.out.println("Viagem cadastrada com sucesso! ID: " + v.getId());
    }

    private static void adicionarAtividade() {
        System.out.print("ID da Viagem: ");
        int idViagem = Integer.parseInt(scanner.nextLine());
        Viagem v = buscarViagem(idViagem);
        if (v == null) return;

        System.out.print("Descrição da atividade: ");
        String desc = scanner.nextLine();
        System.out.print("Custo estimado: ");
        double custo = Double.parseDouble(scanner.nextLine());

        Atividade ativ = new Atividade(idCounterAtiv++, desc, custo, v);
        atividades.add(ativ);
        System.out.println("Atividade adicionada!");
    }

    private static void adicionarAcomodacao() {
        System.out.print("ID da Viagem: ");
        int idViagem = Integer.parseInt(scanner.nextLine());
        Viagem v = buscarViagem(idViagem);
        if (v == null) return;

        System.out.print("Nome da acomodação: ");
        String nome = scanner.nextLine();
        System.out.print("Custo diário: ");
        double custoDia = Double.parseDouble(scanner.nextLine());
        System.out.print("Dias reservados: ");
        int dias = Integer.parseInt(scanner.nextLine());

        Acomodacao acom = new Acomodacao(idCounterAcom++, nome, custoDia, dias, v);
        acomodacoes.add(acom);
        System.out.println("Acomodação adicionada!");
    }

    private static void consultarItinerario() {
        System.out.print("ID da Viagem para consulta: ");
        int idViagem = Integer.parseInt(scanner.nextLine());
        Viagem v = buscarViagem(idViagem);
        if (v == null) return;

        System.out.println("\n--- ITINERÁRIO: " + v.getDestino() + " ---");
        System.out.println("Duração estimada: " + v.calcularDuracaoDias() + " dias.");
        
        System.out.println("\n[Atividades]");
        for (Atividade a : atividades) {
            if (a.getViagem().getId() == v.getId()) {
                System.out.println("- " + a.getDescricao() + " (R$ " + a.getCustoEstimado() + ")");
            }
        }

        System.out.println("\n[Acomodações]");
        for (Acomodacao ac : acomodacoes) {
            if (ac.getViagem().getId() == v.getId()) {
                System.out.println("- " + ac.getNome() + " (Total: R$ " + ac.calcularCustoTotal() + ")");
            }
        }
    }

    private static void calcularOrcamentoTotal() {
        System.out.print("ID da Viagem: ");
        int idViagem = Integer.parseInt(scanner.nextLine());
        Viagem v = buscarViagem(idViagem);
        if (v == null) return;

        Orcamento orcamento = new Orcamento(v);
        for (Atividade a : atividades) {
            if (a.getViagem().getId() == v.getId()) orcamento.adicionarCusto(a.getCustoEstimado());
        }
        for (Acomodacao ac : acomodacoes) {
            if (ac.getViagem().getId() == v.getId()) orcamento.adicionarCusto(ac.calcularCustoTotal());
        }

        System.out.printf("Custo Total da Viagem para %s: R$ %.2f%n", v.getDestino(), orcamento.getTotalGasto());
    }

    private static void consultarViagensPorUsuario() {
        System.out.print("Digite o email do usuário: ");
        String email = scanner.nextLine();
        boolean encontrou = false;

        System.out.println("\nViagens de " + email + ":");
        for (Viagem v : viagens) {
            if (v.getUsuario().getEmail().equalsIgnoreCase(email)) {
                System.out.println("- Destino: " + v.getDestino() + " | Início: " + v.getDataInicio() + " | Fim: " + v.getDataFim());
                encontrou = true;
            }
        }
        if (!encontrou) System.out.println("Nenhuma viagem encontrada para este usuário.");
    }

    private static void gerenciarBagagem() {
        System.out.print("ID da Viagem: ");
        int idViagem = Integer.parseInt(scanner.nextLine());
        Viagem v = buscarViagem(idViagem);
        if (v == null) return;

        System.out.println("1. Adicionar item à mala");
        System.out.println("2. Listar itens da mala");
        int op = Integer.parseInt(scanner.nextLine());

        if (op == 1) {
            System.out.print("Nome do item: ");
            String item = scanner.nextLine();
            boolean sucesso = v.adicionarItemBagagem(item);
            if (sucesso) System.out.println("Item adicionado à bagagem!");
            else System.out.println("A mala está cheia! (Limite do vetor atingido)");
        } else if (op == 2) {
            v.listarBagagem();
        }
    }

    private static Viagem buscarViagem(int id) {
        for (Viagem v : viagens) {
            if (v.getId() == id) return v;
        }
        System.out.println("Viagem não encontrada.");
        return null;
    }

    private static boolean validarDatas(String ini, String fim) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate d1 = LocalDate.parse(ini, dtf);
            LocalDate d2 = LocalDate.parse(fim, dtf);
            return d2.isAfter(d1) || d2.isEqual(d1);
        } catch (Exception e) {
            return false;
        }
    }
}