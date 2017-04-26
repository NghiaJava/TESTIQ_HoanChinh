package com.thanhnghia.testiqnavi;

public class Question {

    //private variables
    int _id;

    String _ques;  // question
    String _ans;   // answer

    String _ansA;
    String _ansB;
    String _ansC;
    String _ansD;

    // Empty constructor
    public Question() {
    }

    // constructor
    public Question(int _id, String _ques, String _ans) {
        this._id = _id;
        this._ques = _ques;
        this._ans = _ans;
    }

    // constructor
    public Question(String _ques, String _ans) {
        this._ques = _ques;
        this._ans = _ans;
    }

    // constructor
    public Question(String _ques, String _ans, String _ansA, String _ansB, String _ansC, String _ansD) {
        this._ques = _ques;
        this._ans = _ans;

        this._ansA = _ansA;
        this._ansB = _ansB;
        this._ansC = _ansC;
        this._ansD = _ansD;
    }

    // constructor
    public Question(int _id, String _ques, String _ans, String _ansA, String _ansB, String _ansC, String _ansD) {
        this._id = _id;
        this._ques = _ques;
        this._ans = _ans;

        this._ansA = _ansA;
        this._ansB = _ansB;
        this._ansC = _ansC;
        this._ansD = _ansD;
    }


    // ---------------------------------------------------------
    public int getID() {

        return this._id;
    }

    public void setID(int id) {

        this._id = id;
    }

    public String getQuestion() {

        return this._ques;
    }

    public void setQues(String _ques) {

        this._ques = _ques;
    }

    public String getAns() {

        return this._ans;
    }

    public void setAns(String _ans) {

        this._ans = _ans;
    }

    public String get_ansA() {

        return _ansA;
    }

    public void set_ansA(String _ansA) {

        this._ansA = _ansA;
    }

    public String get_ansB() {

        return _ansB;
    }

    public void set_ansB(String _ansB) {

        this._ansB = _ansB;
    }

    public String get_ansC() {

        return _ansC;
    }

    public void set_ansC(String _ansC) {

        this._ansC = _ansC;
    }

    public String get_ansD() {

        return _ansD;
    }

    public void set_ansD(String _ansD) {

        this._ansD = _ansD;
    }
}