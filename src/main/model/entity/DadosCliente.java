package main.model.entity;

import java.io.*;
import java.util.Scanner;

public class DadosCliente {

    private static int ultimoID = 0;
    private int id;
    private String nome;
    private String cpf;
    private String rg;
    private String dataNascimento;
    private double limiteCredito;

    // Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getLimiteCredito() {
        return limiteCredito;
    }

    public void setLimiteCredito(double limiteCredito) {
        this.limiteCredito = limiteCredito;
    }

    // Método para imprimir os detalhes do cliente em um arquivo de texto
    public String salvarTxt() {
        try {
            // Incrementar o ID antes de salvar os detalhes do cliente
            ultimoID = encontrarUltimoID();
            this.id = ++ultimoID;

            // Verificar se o ID já existe no arquivo Cliente.txt
            if (idJaExiste()) {
                return "ID já existe. Detalhes do cliente não foram registrados.";
            }

            FileWriter fWriter = new FileWriter("cliente.txt", true);
            PrintWriter pWriter = new PrintWriter(fWriter);

            pWriter.println("\nCliente:");
            pWriter.println("ID: " + this.id);
            pWriter.println("Nome: " + this.nome);
            pWriter.println("CPF: " + this.cpf);
            pWriter.println("RG: " + this.rg);
            pWriter.println("Data de Nascimento: " + this.dataNascimento);
            pWriter.println("Limite de Crédito: R$ " + this.limiteCredito);

            pWriter.close(); // Fechar o PrintWriter para liberar os recursos

            return "Detalhes do cliente registrados com sucesso.";

        } catch (IOException e) {
            e.printStackTrace();
            return "Falha ao registrar os detalhes do cliente. Verifique o arquivo Cliente.txt.";
        }

    }

    // Método para verificar se o ID já existe no arquivo Cliente.txt
    private boolean idJaExiste() {
        try {
            File file = new File("cliente.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains("ID: " + this.id)) {
                    scanner.close();
                    return true;
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static int encontrarUltimoID() {
        int ultimo = 0;
        try {
            File file = new File("Cliente.txt");
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String linha = scanner.nextLine();
                if (linha.startsWith("ID: ")) {
                    int id = Integer.parseInt(linha.substring(4));
                    if (id > ultimo) {
                        ultimo = id;
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return ultimo;
    }

}
