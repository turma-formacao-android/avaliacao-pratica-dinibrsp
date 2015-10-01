package br.com.cast.turmaformacao.agenda.model.entities;


import android.os.Parcel;
import android.os.Parcelable;

public class Social implements Parcelable {

    private Agenda agenda;
    private Long _id;
    private String user;


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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Social social = (Social) o;

        if (agenda != null ? !agenda.equals(social.agenda) : social.agenda != null) return false;
        if (_id != null ? !_id.equals(social._id) : social._id != null) return false;
        return !(user != null ? !user.equals(social.user) : social.user != null);

    }

    @Override
    public int hashCode() {
        int result = agenda != null ? agenda.hashCode() : 0;
        result = 31 * result + (_id != null ? _id.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Social{" +
                "agenda=" + agenda +
                ", _id=" + _id +
                ", user='" + user + '\'' +
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
        dest.writeString(this.user);
    }

    public Social() {
    }

    protected Social(Parcel in) {
        this.agenda = in.readParcelable(Agenda.class.getClassLoader());
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.user = in.readString();
    }

    public static final Creator<Social> CREATOR = new Creator<Social>() {
        public Social createFromParcel(Parcel source) {
            return new Social(source);
        }

        public Social[] newArray(int size) {
            return new Social[size];
        }
    };
}
