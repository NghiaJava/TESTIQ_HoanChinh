package com.thanhnghia.testiqnavi;

public class Player {

    private int _id;
    private String _name;
    private String[] _arrAnsPlayer;
    private String[] _arrAnsCorrect;
    private int _score;

    Player(){
        _id = 0;
        _name = "NULL";
        _arrAnsPlayer = new String[20];
        _arrAnsCorrect  = new String[20];
        _score = 0;

        for(int i = 0 ; i < 20 ; i++){
            _arrAnsPlayer[i] = " ";
            _arrAnsCorrect[i] = " ";
        }
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String[] get_arrAnsPlayer() {
        return _arrAnsPlayer;
    }

    public void set_arrAnsPlayer(String[] _arrAnsPlayer) {
        this._arrAnsPlayer = _arrAnsPlayer;
    }

    public String[] get_arrAnsCorrect() {
        return _arrAnsCorrect;
    }

    public void set_arrAnsCorrect(String[] _arrAnsCorrect) {
        this._arrAnsCorrect = _arrAnsCorrect;
    }

    public int get_score() {
        return _score;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public void setAnsPlayer_qid(int qid, String str){
        _arrAnsPlayer[qid] = str;
    }

    public void setAnsCorrect_qid(int qid, String str){
        _arrAnsCorrect[qid] = str;
    }

    public String getStringArrAnsPlayer(int qid){
        return _arrAnsPlayer[qid];
    }

    public String getStringArrAnsCorrect(int qid){
        return _arrAnsCorrect[qid];
    }
}
