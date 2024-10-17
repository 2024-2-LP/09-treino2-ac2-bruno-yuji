package school.sptech;


import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate data = LocalDate.now();

        Livro livro1 = new Livro("Teste", "David", data);
        livro1.adicionarAvaliacao("bom", 5.0);
        Livro livro2 = new Livro("Teste", "Hal", data);
        livro2.adicionarAvaliacao("mediano", 3.0);
        Livro livro3 = new Livro("Teste", "Oliver", data);
        livro3.adicionarAvaliacao("bom", 4.0);

        Biblioteca biblioteca = new Biblioteca();
        biblioteca.adicionarLivro(livro1);
        biblioteca.adicionarLivro(livro2);
        biblioteca.adicionarLivro(livro3);
        System.out.println(biblioteca.retornarTopCincoLivros());
    }
}