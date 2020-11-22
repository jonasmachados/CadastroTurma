
package ProjetoCadastroTurma.classe;

import java.util.Date;
import java.util.Objects;


public class Turma {
    
    private Integer codigo;
    private String sala;
//    private String dataAbertura;
//    private Date dataFechamento;
    private Professor professor;

    public Turma(Integer codigo, String sala, Professor professor) {
        this.codigo = codigo;
        this.sala = sala;
        this.professor = professor;
    }

    //CONSTRUTOR
    public Turma() {
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.codigo);
        return hash;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turma other = (Turma) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "Turma{" + "codigo=" + codigo + ", sala=" + sala + ", professor=" + professor + '}';
    }

  
    //GET AND SET
    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
  
}
