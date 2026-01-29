package com.example.tp5_carnettel;

/**
 * Created by cordier on 31/01/2017.
 */
import android.os.Parcel;
import android.os.Parcelable;


public class Contact implements Parcelable {
    private String mNom;
    private String mPrenom;
    private String mNumero;

    public Contact(String pNom, String pPrenom, String pNumero) {
        mNom = pNom;
        mPrenom = pPrenom;
        mNumero = pNumero;
    }


    public String getNumero() {
        return mNumero;
    }

    public String getPrenom() {
        return mPrenom;
    }

    public String getNom() {
        return mNom;
    }


    @Override
    public String toString() {
        return mPrenom;
    }

    @Override
    public int describeContents() {        //On renvoie 0, car notre classe ne contient pas de FileDescriptor
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {        // On ajoute les objets dans l'ordre dans lequel on les a déclarés
        dest.writeString(mNom);
        dest.writeString(mPrenom);
        dest.writeString(mNumero);
    }

    public static final Parcelable.Creator<Contact> CREATOR = new Parcelable.Creator<Contact>() {
        @Override
        public Contact createFromParcel(Parcel source) {
            return new Contact(source);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };

    public Contact(Parcel in) {
        mNom = in.readString();
        mPrenom = in.readString();
        mNumero = in.readString();
    }
}