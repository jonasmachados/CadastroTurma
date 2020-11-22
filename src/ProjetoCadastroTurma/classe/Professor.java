
package ProjetoCadastroTurma.classe;

import java.util.Objects;

public class Professor {
    
    private Integer idnome;
    private String nome;
    private String titulacao;

    //CONSTRUTOR
    public Professor(Integer idnome, String nome, String titulacao) {
        this.idnome = idnome;
        this.nome = nome;
        this.titulacao = titulacao;
    }

    //CONSTRUTOR
    public Professor() {
    }

  //TOSTRING 
    @Override
    public String toString() {
        return "Professor{" + "idnome=" + idnome + ", nome=" + nome + ", titulacao=" + titulacao + '}';
    }
    

    
    //EQUAL AND HASHCODE
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.idnome);
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
        final Professor other = (Professor) obj;
        if (!Objects.equals(this.idnome, other.idnome)) {
            return false;
        }
        return true;
    }
    
    
    //GET AND SET
    public Integer getIdnome() {
        return idnome;
    }

    public void setIdnome(Integer idnome) {
        this.idnome = idnome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTitulacao() {
        return titulacao;
    }

    public void setTitulacao(String titulacao) {
        this.titulacao = titulacao;
    }
  
 
}
