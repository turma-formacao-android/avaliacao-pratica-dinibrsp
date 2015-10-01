package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Address implements Parcelable {

    @JsonIgnore
    private Agenda agenda;

    @JsonIgnore
    private Long _id;

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

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (agenda != null ? !agenda.equals(address.agenda) : address.agenda != null) return false;
        if (_id != null ? !_id.equals(address._id) : address._id != null) return false;
        if (bairro != null ? !bairro.equals(address.bairro) : address.bairro != null) return false;
        if (cidade != null ? !cidade.equals(address.cidade) : address.cidade != null) return false;
        if (cep != null ? !cep.equals(address.cep) : address.cep != null) return false;
        if (logradouro != null ? !logradouro.equals(address.logradouro) : address.logradouro != null)
            return false;
        return !(estado != null ? !estado.equals(address.estado) : address.estado != null);

    }

    @Override
    public int hashCode() {
        int result = agenda != null ? agenda.hashCode() : 0;
        result = 31 * result + (_id != null ? _id.hashCode() : 0);
        result = 31 * result + (bairro != null ? bairro.hashCode() : 0);
        result = 31 * result + (cidade != null ? cidade.hashCode() : 0);
        result = 31 * result + (cep != null ? cep.hashCode() : 0);
        result = 31 * result + (logradouro != null ? logradouro.hashCode() : 0);
        result = 31 * result + (estado != null ? estado.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "agenda=" + agenda +
                ", _id=" + _id +
                ", bairro='" + bairro + '\'' +
                ", cidade='" + cidade + '\'' +
                ", cep='" + cep + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.agenda, 0);
        dest.writeValue(this._id);
        dest.writeString(this.bairro);
        dest.writeString(this.cidade);
        dest.writeString(this.cep);
        dest.writeString(this.logradouro);
        dest.writeString(this.estado);
    }

    public Address() {
    }

    protected Address(Parcel in) {
        this.agenda = in.readParcelable(Agenda.class.getClassLoader());
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.bairro = in.readString();
        this.cidade = in.readString();
        this.cep = in.readString();
        this.logradouro = in.readString();
        this.estado = in.readString();
    }

    public static final Creator<Address> CREATOR = new Creator<Address>() {
        public Address createFromParcel(Parcel source) {
            return new Address(source);
        }

        public Address[] newArray(int size) {
            return new Address[size];
        }
    };
}
