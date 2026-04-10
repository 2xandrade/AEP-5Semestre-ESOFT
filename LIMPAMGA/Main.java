import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ServicoSolicitacoes servicoSolicitacoes = new ServicoSolicitacoes();
        int operational;
        
        do {
            System.out.println("\n=== LIMPAMGA - Sistema de Limpeza Urbana ===");
            System.out.println("1 - Criar solicitacao");
            System.out.println("2 - Listar solicitacoes");
            System.out.println("3 - Buscar por protocolo");
            System.out.println("4 - Atualizar status");
            System.out.println("5 - Registrar comentario");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");
            operational = scanner.nextInt();
            scanner.nextLine();
            
            if(operational == 1) {
                System.out.print("Nome do usuario: ");
                String nomeUsuario = scanner.nextLine();
                System.out.print("CPF: ");
                String CPFusuario = scanner.nextLine();
                System.out.print("Telefone: ");
                String telefoneUsuario = scanner.nextLine();
                System.out.print("Descricao do problema: ");
                String descricaoProblema = scanner.nextLine();
                System.out.print("Endereco: ");
                String enderecoUsuario = scanner.nextLine();
                System.out.print("Bairro: ");
                String bairroUsuario = scanner.nextLine();
                System.out.print("Categoria (1-Entulho, 2-Lixo, 3-Outros): ");
                int categoriaOpcao = scanner.nextInt();
                scanner.nextLine();
                
                String categoria;
                if(categoriaOpcao == 1) categoria = "Entulho";
                else if(categoriaOpcao == 2) categoria = "Lixo";
                else categoria = "Outros";
                
                Usuario usuario = new Usuario();
                usuario.nome = nomeUsuario;
                usuario.cpf = CPFusuario;
                usuario.tel = telefoneUsuario;
                
                Solicitacao solicitacao = new Solicitacao();
                solicitacao.protocolo = servicoSolicitacoes.gerarProt();
                solicitacao.desc = descricaoProblema;
                solicitacao.end = enderecoUsuario;
                solicitacao.bairro = bairroUsuario;
                solicitacao.categoria = categoria;
                solicitacao.status = StatusEnum.valueOf("ABERTO");
                solicitacao.usuario = usuario;
                solicitacao.historicoStatuses = new ArrayList<>();
                solicitacao.comentarios = new ArrayList<>();
                
                HistoricoStatus historicoStatus = new HistoricoStatus();
                historicoStatus.data = new Date();
                historicoStatus.status = "Aberto";
                historicoStatus.observacao = "Solicitacao criada";
                solicitacao.historicoStatuses.add(historicoStatus);
                
                servicoSolicitacoes.lista.add(solicitacao);
                System.out.println("Solicitacao criada! Protocolo: " + solicitacao.protocolo);
            }
            else if(operational == 2) {
                if(servicoSolicitacoes.total() == 0) {
                    System.out.println("Nenhuma solicitacao cadastrada.");
                } else {
                    System.out.println("1 - Listar todas as solicitações");
                    System.out.println("2 - Listar solicitações por status");
                    System.out.println("Opcao: ");
                    int opcao = scanner.nextInt();
                    scanner.nextLine();

                    List<Solicitacao> listaAExibir;

                    if (opcao == 2){
                        System.out.println("Status (1-Em analise, 2-Em andamento, 3-Concluido, 4-Cancelado): ");
                        int opcaoStatus = scanner.nextInt();
                        scanner.nextLine();
                        StatusEnum status = StatusEnum.fromCodigo(opcaoStatus);
                        listaAExibir = servicoSolicitacoes.listarPorStatus(status);
                    } else {
                        listaAExibir = servicoSolicitacoes.lista;
                    }

                    for(int i = 0; i < listaAExibir.size(); i++){
                        Solicitacao solicitacao = listaAExibir.get(i);
                        System.out.println("\n--- Solicitacao #" + (i+1) + " ---");
                        solicitacao.imprimirDadosSolicitacao();
                    }
                }
            }
            else if(operational == 3) {
                System.out.print("Digite o protocolo: ");
                String protocoloBuscado = scanner.nextLine();
                Optional<Solicitacao> resultado = servicoSolicitacoes.buscarProtocolo(protocoloBuscado);
                if (resultado.isPresent()){
                    Solicitacao solicitacao = resultado.get();
                    System.out.println("\n--- DADOS DA SOLICITACAO ---");

                    solicitacao.imprimirDadosSolicitacao();

                }else {
                    System.out.println("Protocolo não encontrado!");
                }
            }
            else if(operational == 4) {
                System.out.print("Protocolo: ");
                String protocoloBuscado = scanner.nextLine();
                Optional<Solicitacao> resultado = servicoSolicitacoes.buscarProtocolo(protocoloBuscado);
                if (resultado.isPresent()) {
                    Solicitacao solicitacao = resultado.get();
                    System.out.println("Status atual: " + solicitacao.getStatus());
                    System.out.println("Novo status (1-Em analise, 2-Em andamento, 3-Concluido, 4-Cancelado): ");
                    int opcao = scanner.nextInt();
                    scanner.nextLine();
                    StatusEnum novoStatus = StatusEnum.fromCodigo(opcao);
                    if (novoStatus == null){
                        System.out.println("Opção inválida!");
                        continue;
                    }

                    System.out.print("Observacao: ");
                    String obs = scanner.nextLine();

                    boolean atualizado = servicoSolicitacoes.atualizarStatus(protocoloBuscado, novoStatus);

                    if (atualizado) {
                        System.out.println("Status atualizado!");
                    } else {
                        System.out.println("Erro ao atualizar!");
                    }

                }else{
                    System.out.println("Protocolo nao encontrado!");
                }
            }
            else if(operational == 5) {
                System.out.print("Protocolo: ");
                String protocoloBuscado = scanner.nextLine();
                Optional<Solicitacao> resultado = servicoSolicitacoes.buscarProtocolo(protocoloBuscado);
                if (resultado.isPresent()){
                    Solicitacao solicitacao = resultado.get();
                    System.out.print("Digite o comentario: ");
                    String comentario = scanner.nextLine();
                    solicitacao.comentarios.add(comentario);
                    System.out.println("Comentario registrado!");
                }else {
                    System.out.println("Protocolo nao encontrado!");
                }
            }
        } while(operational != 0);
        
        System.out.println("Sistema encerrado.");
    }
}
