package com.example.e2507235.tp_list_etudiant;

/**
 * Created by e2507235 on 29/01/2026.
 */
public class Etudiant {

    private String name;
    private String note_info;
    private String note_math;

    public Etudiant(String name, String note_info, String note_math) {
        this.name = name;
        this.note_info = note_info;
        this.note_math = note_math;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote_info() {
        return note_info;
    }

    public void setNote_info(String note_info) {
        this.note_info = note_info;
    }

    public String getNote_math() {
        return note_math;
    }

    public void setNote_math(String note_math) {
        this.note_math = note_math;
    }

    @Override
    public String toString() {
        return name;
    }



}
