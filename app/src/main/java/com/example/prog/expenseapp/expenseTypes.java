package com.example.prog.expenseapp;

/**
 * Created by Noam on 07/05/2018.
 */

public class expenseTypes {
    private int _id;
    private String _expenseTypeName;

    public expenseTypes() {

    }

    public expenseTypes(String _expenseTypeName) {
        this._expenseTypeName = _expenseTypeName;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void set_expenseTypeName(String _expenseTypeName) {
        this._expenseTypeName = _expenseTypeName;
    }

    public int get_id() {
        return _id;
    }

    public String get_expenseTypeName() {
        return _expenseTypeName;
    }


}
