package cadastrodealunos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

class Aluno {

    private int matricula;
    private String nome;
    private String dataNascimento;
    private char sexo;
    private String email;

    public Aluno(int m, String n, String d, char s, String e) {
        matricula = m;
        nome = n;
        dataNascimento = d;
        sexo = s;
        email = e;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNome(String n) {
        nome = n;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public String getEmail() {
        return email;
    }

}

public class CadastroDeAlunos {

    private static final int OPCAO_INSERIR = 1;
    private static final int OPCAO_ALTERAR = 2;
    private static final int OPCAO_LISTAR = 3;
    private static final int OPCAO_EXCLUIR = 4;
    private static final int OPCAO_SAIR = 5;

    private static ArrayList<Aluno> colecaoAluno;

    public static void main(String[] args) throws IOException {
        BufferedReader entrada = new BufferedReader(
                new InputStreamReader(System.in));
        colecaoAluno = new ArrayList<>();

        int opcao = 0;
        while (opcao != OPCAO_SAIR) {
            System.out.println("Opções:");
            System.out.println(" 1 - Inserir registro de aluno");
            System.out.println(" 2 - Alterar registro de aluno");
            System.out.println(" 3 - Listar registros de alunos");
            System.out.println(" 4 - Excluir registro de aluno");
            System.out.println(" 5 - Sair do programa");
            System.out.print("Digite a sua opção e tecle ENTER: ");

            try {
                opcao = Integer.parseInt(entrada.readLine());
            } catch (NumberFormatException e) {
                System.out.println("\nOPÇÃO INVÁLIDA!\n");
                continue;
            }

            switch (opcao) {
                case OPCAO_INSERIR:
                    System.out.println("Inserir registro de aluno");
                    System.out.print(" Matrícula.........: ");
                    int matricula = Integer.parseInt(entrada.readLine());
                    System.out.print(" Nome..............: ");
                    String nome = entrada.readLine();
                    System.out.print(" Data de nascimento: ");
                    String dataNascimento = entrada.readLine();
                    System.out.print(" Sexo(M/F).........: ");
                    char sexo = entrada.readLine().charAt(0);
                    System.out.print(" Email.............: ");
                    String email = entrada.readLine();
                    System.out.print("Confirma inserção? (s/n): ");
                    char confirmacao = entrada.readLine().charAt(0);
                    if (confirmacao == 's') {
                        Aluno aluno = new Aluno(matricula, nome, dataNascimento,
                                sexo, email);
                        colecaoAluno.add(aluno);
                        System.out.println("\nRegistro de aluno foi inserido!");
                    }
                    break;
                case OPCAO_ALTERAR:
                    System.out.println("Alterar registro de aluno");
                    System.out.print("Informe a matrícula do aluno: ");
                    matricula = Integer.parseInt(entrada.readLine());
                    Aluno a = buscarAluno(matricula);
                    nome = a.getNome();
                    dataNascimento = a.getDataNascimento();
                    sexo = a.getSexo();
                    email = a.getEmail();
                    if (a != null) {
                        System.out.print(" Matrícula: " + a.getMatricula() + " | Deseja alterar? (s/n) ");
                        char alterar = entrada.readLine().charAt(0);
                        if (alterar == 's') {
                            System.out.print(" Matrícula: ");
                            matricula = Integer.parseInt(entrada.readLine());
                        }
                        System.out.print(" Nome: " + a.getNome() + " | Deseja alterar? (s/n) ");
                        alterar = entrada.readLine().charAt(0);
                        if (alterar == 's') {
                            System.out.print(" Nome: ");
                            nome = entrada.readLine();
                        }
                        System.out.print(" Data de nascimento: " + a.getDataNascimento() + " | Deseja alterar? (s/n) ");
                        alterar = entrada.readLine().charAt(0);
                        if (alterar == 's') {
                            System.out.print(" Data de nascimento: ");
                            dataNascimento = entrada.readLine();
                        }
                        System.out.print(" Sexo : " + a.getSexo() + " | Deseja alterar? (s/n) ");
                        alterar = entrada.readLine().charAt(0);
                        if (alterar == 's') {
                            System.out.print(" Sexo: ");
                            sexo = entrada.readLine().charAt(0);
                        }
                        System.out.print(" Email: " + a.getEmail() + " | Deseja alterar? (s/n) ");
                        alterar = entrada.readLine().charAt(0);
                        if (alterar == 's') {
                            System.out.print(" Email: ");
                            email = entrada.readLine();
                        }
                        System.out.print("Confirma alterações? (s/n): ");
                        confirmacao = entrada.readLine().charAt(0);
                        if (confirmacao == 's') {
                            a.setMatricula(matricula);
                            a.setNome(nome);
                            a.setDataNascimento(dataNascimento);
                            a.setSexo(sexo);
                            a.setEmail(email);
                        }
                    }
                    break;
                case OPCAO_LISTAR:
                    System.out.println("Listagem de registros de alunos");
                    System.out.println("Matrícula | Nome                           | Data nasc. | Sexo | Email                          ");
                    System.out.println("----------+--------------------------------+------------+------+--------------------------------");
  
                    int i = -1;
                    for (Aluno aluno : colecaoAluno) {
                        i++;
//                  for (int i = 0; i < colecaoAluno.size(); i++) {
//                      a = colecaoAluno.get(i);
                        System.out.printf("%-9d | %-30s | %-10s | %-4s | %-30s\n",
                                aluno.getMatricula(), aluno.getNome(),
                                aluno.getDataNascimento(), aluno.getSexo(), aluno.getEmail());
                        System.out.println("----------+--------------------------------+------------+------+--------------------------------");
                    }
                    break;
                case OPCAO_EXCLUIR:
                    System.out.println("Excluir registro de aluno");
                    System.out.print("Informe o número de matrícula: ");
                    matricula = Integer.parseInt(entrada.readLine());
                    boolean foiExcluido = false;
                    for (i = 0; i < colecaoAluno.size(); i++) {
                        if (colecaoAluno.get(i).getMatricula() == matricula) {
                            colecaoAluno.remove(i);
                            foiExcluido = true;
                            System.out.println("\nAluno foi excluído com sucesso!");
                            break;
                        }
                    }
                    if (!foiExcluido) {
                        System.out.println("\nAluno não encontrado!");
                    }
                    break;
            }
            if (opcao != OPCAO_SAIR) {
                System.out.print("\n[pressione ENTER para continuar]...");
                entrada.readLine();
            }
        }
    }
    
    private static Aluno buscarAluno(int matricula) {
        for (Aluno a : colecaoAluno) {
            if (a.getMatricula() == matricula)
                return a;
        }
        return null;
    }

}
