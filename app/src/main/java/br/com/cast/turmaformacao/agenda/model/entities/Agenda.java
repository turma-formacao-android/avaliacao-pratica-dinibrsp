package br.com.cast.turmaformacao.agenda.model.entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Agenda implements Parcelable {

    private Long _id;
    private String name;
    private List<Tel> tels;
    private List<Mail> mails;
    private List<Social> socials;
    private Address address;

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

    public List<Tel> getTels() {
        return tels;
    }

    public void setTels(List<Tel> tels) {
        this.tels = tels;
    }

    public List<Mail> getMails() {
        return mails;
    }

    public void setMails(List<Mail> mails) {
        this.mails = mails;
    }

    public List<Social> getSocials() {
        return socials;
    }

    public void setSocials(List<Social> socials) {
        this.socials = socials;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Agenda agenda = (Agenda) o;

        if (_id != null ? !_id.equals(agenda._id) : agenda._id != null) return false;
        if (name != null ? !name.equals(agenda.name) : agenda.name != null) return false;
        if (tels != null ? !tels.equals(agenda.tels) : agenda.tels != null) return false;
        if (mails != null ? !mails.equals(agenda.mails) : agenda.mails != null) return false;
        if (socials != null ? !socials.equals(agenda.socials) : agenda.socials != null)
            return false;
        return !(address != null ? !address.equals(agenda.address) : agenda.address != null);

    }

    @Override
    public int hashCode() {
        int result = _id != null ? _id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tels != null ? tels.hashCode() : 0);
        result = 31 * result + (mails != null ? mails.hashCode() : 0);
        result = 31 * result + (socials != null ? socials.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "_id=" + _id +
                ", name='" + name + '\'' +
                ", tels=" + tels +
                ", mails=" + mails +
                ", socials=" + socials +
                ", address=" + address +
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
        dest.writeTypedList(tels);
        dest.writeTypedList(mails);
        dest.writeTypedList(socials);
        dest.writeParcelable(this.address, 0);
    }

    public Agenda() {
    }

    protected Agenda(Parcel in) {
        this._id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.tels = in.createTypedArrayList(Tel.CREATOR);
        this.mails = in.createTypedArrayList(Mail.CREATOR);
        this.socials = in.createTypedArrayList(Social.CREATOR);
        this.address = in.readParcelable(Address.class.getClassLoader());
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
