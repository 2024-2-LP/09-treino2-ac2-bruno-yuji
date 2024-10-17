package school.sptech;

import school.sptech.exception.ArgumentoInvalidoException;
import school.sptech.exception.LivroNaoEncontradoException;

import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private String nome;
    private List<Livro> livros;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public Biblioteca() {
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        if (livro != null && livro.getAutor() != null && !livro.getAutor().isBlank() &&
                !livro.getAutor().isEmpty() && livro.getTitulo() != null &&
                !livro.getTitulo().isBlank() && !livro.getTitulo().isEmpty() &&
                 livro.getDataPublicacao() != null) {
            livros.add(livro);
        } else {
            throw new ArgumentoInvalidoException("Os valores passados são inválidos!");
        }
    }

    public void removerLivroPorTitulo(String titulo) {
        if (titulo != null && !titulo.isBlank() && !titulo.isEmpty()) {
            Boolean encontrado = false;
            for (int i = 0; i < livros.size(); i++) {
                if (livros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                    livros.remove(i);
                    encontrado = true;
                }
            }
            if (!encontrado) {
                throw new LivroNaoEncontradoException("O livro não foi encontrado.");
            }
        } else {
            throw new ArgumentoInvalidoException("Título inválido!");
        }
    }

    public Livro buscarLivroPorTitulo(String titulo) {
        if (titulo != null && !titulo.isBlank() && !titulo.isEmpty()) {
            for (Livro livro : livros) {
                if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                    return livro;
                }
            }
            throw new LivroNaoEncontradoException("O livro não foi encontrado.");
        }
        throw new ArgumentoInvalidoException("Título inválido!");
    }

    public Integer contarLivros() {
        return livros.size();
    }

    public List<Livro> obterLivrosAteAno(Integer ano) {
        List<Livro> livrosPublicados = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getDataPublicacao().getYear() <= ano) {
                livrosPublicados.add(livro);
            }
        }
        return livrosPublicados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Livro> retornarTopCincoLivros() {
        List<Livro> top5 = new ArrayList<>();
        if (livros.size() > 0) {
            if (livros.size() == 1) {
                top5.add(livros.getFirst());
            } else {
                List<Livro> livrosCopia = this.livros;
                Livro livroTemporario;
                for (int cont = 0; cont < livros.size()-1; cont++) {
                    for (int i = 0; i < livros.size()-1; i++) {
                        if (livrosCopia.get(i).calcularMediaAvaliacoes() < livrosCopia.get(i+1).calcularMediaAvaliacoes()) {
                            livroTemporario = livrosCopia.get(i);
                            livrosCopia.set(i, livrosCopia.get(i+1));
                            livrosCopia.set(i+1, livroTemporario);
                        }
                    }
                }
                Integer tamanho;
                if (livros.size() < 5) {
                    tamanho = livros.size();
                } else {
                    tamanho = 5;
                }
                for (int i = 0; i < tamanho; i++) {
                    top5.add(livrosCopia.get(i));
                }
            }
        }
        return top5;
    }
}
