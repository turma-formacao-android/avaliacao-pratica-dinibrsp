package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrador on 02/10/2015.
 */
public class Telefone implements Parcelable {

    private Long _id;
    private String numero;
    private Agenda agenda;

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Telefone telefone = (Telefone) o;

        if (_id != null ? !_id.equals(telefone._id) : telefone._id != null) return false;
        if (numero != null ? !numero.equals(telefone.numero) : telefone.numero != null)
            return false;
        return !(agenda != null ? !agenda.equals(telefone.agenda) : telefone.agenda != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (numero != null ? numero.hashCode() : 0);
        result = 31 * result + (agenda != null ? agenda.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "_id=" + _id +
                ", numero='" + numero + '\'' +
                ", agenda=" + agenda +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._id);
        dest.writeString(this.numero);
        dest.writeParcelable(this.agenda, 0);
    }

    public Telefone() {
    }

    protected Telefone(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.numero = in.readString();
        this.agenda = in.readParcelable(Agenda.class.getClassLoader());
    }

    public static final Creator<Telefone> CREATOR = new Creator<Telefone>() {
        public Telefone createFromParcel(Parcel source) {
            return new Telefone(source);
        }

        public Telefone[] newArray(int size) {
            return new Telefone[size];
        }
    };
}
