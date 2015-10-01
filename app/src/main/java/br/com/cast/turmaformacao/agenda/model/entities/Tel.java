package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Tel implements Parcelable {


    private Agenda agenda;
    private Long _id;
    private String number;


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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tel tel = (Tel) o;

        if (agenda != null ? !agenda.equals(tel.agenda) : tel.agenda != null) return false;
        if (_id != null ? !_id.equals(tel._id) : tel._id != null) return false;
        return !(number != null ? !number.equals(tel.number) : tel.number != null);

    }

    @Override
    public int hashCode() {
        int result = agenda != null ? agenda.hashCode() : 0;
        result = 31 * result + (_id != null ? _id.hashCode() : 0);
        result = 31 * result + (number != null ? number.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Tel{" +
                "agenda=" + agenda +
                ", _id=" + _id +
                ", number='" + number + '\'' +
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
        dest.writeString(this.number);
    }

    public Tel() {
    }

    protected Tel(Parcel in) {
        this.agenda = in.readParcelable(Agenda.class.getClassLoader());
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.number = in.readString();
    }

    public static final Creator<Tel> CREATOR = new Creator<Tel>() {
        public Tel createFromParcel(Parcel source) {
            return new Tel(source);
        }

        public Tel[] newArray(int size) {
            return new Tel[size];
        }
    };
}
