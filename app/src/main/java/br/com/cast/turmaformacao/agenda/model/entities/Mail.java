package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class Mail implements Parcelable {

    private Agenda agenda;
    private Long _id;
    private String email;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Mail mail = (Mail) o;

        if (agenda != null ? !agenda.equals(mail.agenda) : mail.agenda != null) return false;
        if (_id != null ? !_id.equals(mail._id) : mail._id != null) return false;
        return !(email != null ? !email.equals(mail.email) : mail.email != null);

    }

    @Override
    public int hashCode() {
        int result = agenda != null ? agenda.hashCode() : 0;
        result = 31 * result + (_id != null ? _id.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "agenda=" + agenda +
                ", _id=" + _id +
                ", email='" + email + '\'' +
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
        dest.writeString(this.email);
    }

    public Mail() {
    }

    protected Mail(Parcel in) {
        this.agenda = in.readParcelable(Agenda.class.getClassLoader());
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.email = in.readString();
    }

    public static final Creator<Mail> CREATOR = new Creator<Mail>() {
        public Mail createFromParcel(Parcel source) {
            return new Mail(source);
        }

        public Mail[] newArray(int size) {
            return new Mail[size];
        }
    };
}

