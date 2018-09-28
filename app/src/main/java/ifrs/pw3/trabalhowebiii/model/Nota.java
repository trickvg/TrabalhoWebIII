package ifrs.pw3.trabalhowebiii.model;

public class Nota {

    private Integer _id_nota;
    private String titulo_nota;
    private String descricao_nota;
    private String local_evento_nota;
    private String data_nota;

    public Nota() {
    }

    public Nota(Integer _id_nota, String titulo_nota, String descricao_nota) {
        this._id_nota = _id_nota;
        this.titulo_nota = titulo_nota;
        this.descricao_nota = descricao_nota;
    }

    public Integer get_id_nota() {
        return _id_nota;
    }

    public void set_id_nota(Integer _id_nota) {
        this._id_nota = _id_nota;
    }

    public String getTitulo_nota() {
        return titulo_nota;
    }

    public void setTitulo_nota(String titulo_nota) {
        this.titulo_nota = titulo_nota;
    }

    public String getDescricao_nota() {
        return descricao_nota;
    }

    public void setDescricao_nota(String descricao_nota) {
        this.descricao_nota = descricao_nota;
    }

    public String getLocal_evento_nota() {
        return local_evento_nota;
    }

    public void setLocal_evento_nota(String local_evento_nota) {
        this.local_evento_nota = local_evento_nota;
    }

    public String getData_nota() {
        return data_nota;
    }

    public void setData_nota(String data_nota) {
        this.data_nota = data_nota;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "_id_nota=" + _id_nota +
                ", titulo_nota='" + titulo_nota + '\'' +
                ", descricao_nota='" + descricao_nota + '\'' +
                ", local_evento_nota='" + local_evento_nota + '\'' +
                ", data_nota='" + data_nota + '\'' +
                '}';
    }
}
