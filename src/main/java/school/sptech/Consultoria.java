package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {
    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
        this.desenvolvedores = new ArrayList<>();
    }

    public Consultoria() {
        this.desenvolvedores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public List<Desenvolvedor> getDesenvolvedores() {
        return desenvolvedores;
    }

    public Boolean contratar(Desenvolvedor desenvolvedor) {
        if (desenvolvedores.size() >= vagas) {
            return false;
        }
        desenvolvedores.add(desenvolvedor);
        return true;
    }

    public Boolean contratarFullstack(DesenvolvedorWeb desenvolvedor) {
        if (desenvolvedor.isFullstack()) {
            desenvolvedores.add(desenvolvedor);
            return true;
        }
        return false;
    }

    public Double getTotalSalarios() {
        Double salario = 0.0;
        for (Desenvolvedor desenvolvedorAtual : desenvolvedores) {
            salario += desenvolvedorAtual.calcularSalario();
        }
        return salario;
    }

    public Integer qtdDesenvolvedoresMobile() {
        Integer contador = 0;
        for (Desenvolvedor desenvolvedorAtual : desenvolvedores) {
            if (desenvolvedorAtual instanceof DesenvolvedorMobile) {
                contador++;
            }
        }
        return contador;
    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario) {
        List<Desenvolvedor> desenvolvedorSalarioMaiorQue = new ArrayList<>();

        for (Desenvolvedor desenvolvedorAtual : desenvolvedores) {
            if (desenvolvedorAtual.calcularSalario() >= salario) {
                desenvolvedorSalarioMaiorQue.add(desenvolvedorAtual);
            }
        }
        return desenvolvedorSalarioMaiorQue;
    }

    public Desenvolvedor buscarMenorSalario() {
        if (desenvolvedores.isEmpty()) {
            return null;
        }

        Desenvolvedor devMenorSalario = desenvolvedores.get(0);
        for (Desenvolvedor desenvolvedorAtual : desenvolvedores) {
            if (desenvolvedorAtual.calcularSalario() < devMenorSalario.calcularSalario()) {
                devMenorSalario = desenvolvedorAtual;
            }
        }
        return devMenorSalario;
    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia) {
        List<Desenvolvedor> devPorTecnologia = new ArrayList<>();

        for (Desenvolvedor desenvolvedorAtual : desenvolvedores) {
            if (desenvolvedorAtual instanceof DesenvolvedorMobile) {
                if (((DesenvolvedorMobile) desenvolvedorAtual).getLinguagem().equalsIgnoreCase(tecnologia) ||
                        ((DesenvolvedorMobile) desenvolvedorAtual).getPlataforma().equalsIgnoreCase(tecnologia)) {
                    devPorTecnologia.add(desenvolvedorAtual);
                }
            }

            if (desenvolvedorAtual instanceof DesenvolvedorWeb) {
                if (((DesenvolvedorWeb) desenvolvedorAtual).getBackend().equalsIgnoreCase(tecnologia) ||
                        ((DesenvolvedorWeb) desenvolvedorAtual).getFrontend().equalsIgnoreCase(tecnologia) ||
                        ((DesenvolvedorWeb) desenvolvedorAtual).getSgbd().equalsIgnoreCase(tecnologia)) {
                    devPorTecnologia.add(desenvolvedorAtual);
                }
            }
        }
        return devPorTecnologia;
    }

    public Double getTotalSalariosPorTecnologia(String tecnologia) {
        List<Desenvolvedor> devsPorTech = buscarPorTecnologia(tecnologia);
        Double salario = 0.0;

        for (Desenvolvedor devAtual : devsPorTech) {
            salario += devAtual.calcularSalario();
        }
        return salario;
    }
}
