package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({ "estado_info", "area_km2", "codigo_ibge", "nome", "cidade_info", "complemento" })
public class Agenda implements Parcelable {
    @JsonIgnore
    private Long _id;
    @JsonIgnore
    private String name;
    @JsonIgnore
    private String tel;
    @JsonIgnore
    private String mail;
    @JsonIgnore
    private String social;

    @JsonProperty("bairro")
    private String bairro;

    @JsonProperty("cidade")
    private String cidade;

    @JsonProperty("cep")
    private String cep;

    @JsonProperty("logradouro")
    private String logradouro;

    @JsonProperty("estado")
    private String estado;

    @JsonIgnore
    private List<Telefone> tels;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getSocial() {
        return social;
    }

    public void setSocial(String social) {
        this.social = social;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Telefone> getTels() {
        return tels;
    }

    public void setTels(List<Telefone> tels) {
        this.tels = tels;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agenda agenda = (Agenda) o;

        if (_id != null ? !_id.equals(agenda._id) : agenda._id != null) return false;
        if (name != null ? !name.equals(agenda.name) : agenda.name != null) return false;
        if (tel != null ? !tel.equals(agenda.tel) : agenda.tel != null) return false;
        if (mail != null ? !mail.equals(agenda.mail) : agenda.mail != null) return false;
        if (social != null ? !social.equals(agenda.social) : agenda.social != null) return false;
        if (bairro != null ? !bairro.equals(agenda.bairro) : agenda.bairro != null) return false;
        if (cidade != null ? !cidade.equals(agenda.cidade) : agenda.cidade != null) return false;
        if (cep != null ? !cep.equals(agenda.cep) : agenda.cep != null) return false;
        if (logradouro != null ? !logradouro.equals(agenda.logradouro) : agenda.logradouro != null)
            return false;
        if (estado != null ? !estado.equals(agenda.estado) : agenda.estado != null) return false;
        return !(tels != null ? !tels.equals(agenda.tels) : agenda.tels != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tel != null ? tel.hashCode() : 0);
        result = 31 * result + (mail != null ? mail.hashCode() : 0);
        result = 31 * result + (social != null ? social.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (logradouro != null ? logradouro.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        result = 31 * result + (tels != null ? tels.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", tel='" + tel + '\'' +
                ", mail='" + mail + '\'' +
                ", social='" + social + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", estado='" + estado + '\'' +
                ", tels=" + tels +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.name);
        dest.writeString(this.tel);
        dest.writeString(this.mail);
        dest.writeString(this.social);
        dest.writeString(this.bairro);
        dest.writeString(this.cidade);
        dest.writeString(this.cep);
        dest.writeString(this.logradouro);
        dest.writeString(this.estado);
        dest.writeTypedList(tels);
    }

    public Agenda() {
        super();
        tels = new ArrayList<>();
    }

    protected Agenda(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.tel = in.readString();
        this.mail = in.readString();
        this.social = in.readString();
        this.bairro = in.readString();
        this.cidade = in.readString();
        this.cep = in.readString();
        this.logradouro = in.readString();
        this.estado = in.readString();
        this.tels = in.createTypedArrayList(Telefone.CREATOR);
    }

    public static final Creator<Agenda> CREATOR = new Creator<Agenda>() {
        public Agenda createFromParcel(Parcel source) {
            return new Agenda(source);
        }

        public Agenda[] newArray(int size) {
            return new Agenda[size];
        }
    };
}
