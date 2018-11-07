package ifrs.pw3.trabalhowebiii.model;

public class Pessoa {

    private Integer _id;
    private String nome;
    private String senha;
    private String endereco;
    private String telefone;

    public Pessoa() {
    }

    public Pessoa(Integer _id, String nome, String senha) {
        this._id = _id;
        this.nome = nome;
        this.senha = senha;
    }

    public Pessoa(Integer _id, String nome, String senha, String endereco, String telefone) {
        this._id = _id;
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "_id=" + _id +
                ", nome='" + nome + '\'' +
                ", senha='" + senha + '\'' +
                ", endereco='" + endereco + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
