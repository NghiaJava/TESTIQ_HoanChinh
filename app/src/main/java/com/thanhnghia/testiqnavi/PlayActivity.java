package com.thanhnghia.testiqnavi;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    // khai bao
    private TextView txtScore;
    private TextView txtTime;
    private TextView txtQuestion;
    private Button btnAns1;
    private Button btnAns2;
    private Button btnAns3;
    private Button btnAns4;

    private Button btnBack;
    private Button btnNext;

    private List<Question> quesList;
    private int qid = 0;
    private Question currentQ;
    private int score = 0;

    private Player player01;
    //private int[] arrLog;
    CountDownTimer countDownTimer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


        DatabaseHandler db = new DatabaseHandler(this);

        // Inserting Contacts
        Log.d("Insert: ", "Inserting ..");
        try {
            if (db.getAllQuestions().size () == 0) {
                db.addQuestion (new Question
                        ("Câu 1:\n "
                                + "Số tiếp theo của dãy số:\n"
                                + " 1, 1, 2, 3, 5 ?"
                                + "\n \n \n",
                        "8",
                        "6",
                        "7",
                        "8",
                        "9"));
                //2
                db.addQuestion (new Question
                        ("Câu 2:\n "
                                + "Hãy tìm số tiếp theo của dãy số:\n"
                                + " 1, 5, 13, 25, ?"
                                + "\n \n \n",
                                "41",
                                "36",
                                "41",
                                "49",
                                "62"));
                //3
                db.addQuestion (new Question
                        ("Câu 3:\n "
                                + "Tìm số xuất hiện tiếp theo:\n"
                                + "25, 32, 27, 36, ?"
                                + "\n \n \n",
                                "27",
                                "42",
                                "27",
                                "35",
                                "39"));
                //4
                db.addQuestion (new Question
                        ("Câu 4:\n "
                                + "Tìm số thích hợp thay thế vào ô có dấu ?:\n"
                                + "926 \t 24 \n"
                                + "799 \t 72 \n 956 \t ? \n ",
                                "51",
                                "51",
                                "15",
                                "46",
                                "64"));
                //5
                db.addQuestion (new Question
                        ("Câu 5:\n "
                                + "Tìm số thích hợp thay thế vào ô có dấu ?:\n"
                                + "9 – 6 - 1; 27 – 1 - 2; 6 - 3 - ?"
                                + "\n \n \n",
                                "3",
                                "2",
                                "3",
                                "4",
                                "5"));
                //6
                db.addQuestion (new Question
                        ("Câu 6:\n "
                                + "Số tiếp theo của dãy 19, 28, 37, 46, ... là số nào? \n"
                                + ""
                                + "\n \n \n",
                                "55",
                                "79",
                                "55",
                                "49",
                                "67"));
                //7
                db.addQuestion (new Question
                        ("Câu 7:\n "
                                + "Hãy tính dãy số sau đây:\n"
                                + " 1 + 2 + 3 + ..... + 99 = ........... "
                                + "\n \n \n",
                                "4950",
                                "4950",
                                "4500",
                                "4850",
                                "4650"));
                //8
                db.addQuestion (new Question
                        ("Câu 8:\n "
                                + "Từ nào sau đây có thể loại bỏ? \n"
                                + "  "
                                + "\n \n \n",
                                "V0GNRÒTN",
                                "BÀNYTA",
                                "ÙIĐ",
                                "AIV",
                                "V0GNRÒTN"));
                //9
                db.addQuestion (new Question
                        ("Câu 9:\n "
                                + "Nếu 5y – 3x = 7 và \n"
                                + "6y + 6x = 2 thì giá trị của y là: "
                                + "\n \n \n",
                                "1",
                                "1",
                                "2",
                                "3",
                                "4"));
                //10
                db.addQuestion (new Question
                        ("Câu 10:\n "
                                + "Ký tự tiếp theo trong dãy sau đây là ký tự nào:\n"
                                + "A…C…F…J…O… ?"
                                + "\n \n \n",
                                "U",
                                "S",
                                "T",
                                "U",
                                "V"));
                //11
                db.addQuestion (new Question
                        ("Câu 11:\n "
                                + "Tìm số tiếp theo của dãy:\n"
                                + "1 - 1 - 2 - 3 - 5 - 8 - 13 - ? :"
                                + "\n \n \n",
                                "21",
                                "8",
                                "13",
                                "21",
                                "26"));
                //12
                db.addQuestion (new Question
                        ("Câu 12:\n "
                                + "Từ PEACH được viết là HCAEP,\n"
                                + "số 46251 sẽ được viết như thế nào?"
                                + "\n \n \n",
                                "15264",
                                "26451",
                                "51462",
                                "12654",
                                "15264"));
                //13
                db.addQuestion (new Question
                        ("Câu 13:\n "
                                + "Số nào không thuộc dãy số sau?\n"
                                + "1 - 2 - 5 - 10 - 13 - 26 - 29 - 48"
                                + "\n \n \n",
                                "48",
                                "5",
                                "26",
                                "29",
                                "48"));
                //14
                db.addQuestion (new Question
                        ("Câu 14:\n "
                                + "Số nào còn thiếu trong dấu hỏi chấm:\n"
                                + "1 - 8 - 27 - ? - 125 - 216:"
                                + "\n \n \n",
                                "64",
                                "45",
                                "64",
                                "46",
                                "99"));
                //15
                db.addQuestion (new Question
                        ("Câu 15:\n "
                                + "Cho dãy số : 16, 06, 68, 88, x , 98.\n"
                                + "x sẽ là : "
                                + "\n \n \n",
                                "87",
                                "89",
                                "90",
                                "87",
                                "96"));
                //16
                db.addQuestion (new Question
                        ("Câu 16:\n "
                                + "Ký tự tiếp theo trong dãy sau đây là ký tự nào:\n"
                                + "A…C…F…J…O… ?"
                                + "\n \n \n",
                                "U",
                                "S",
                                "T",
                                "U",
                                "V"));
                //17
                db.addQuestion (new Question
                        ("Câu 17:\n "
                                + "Tìm số tiếp theo của dãy số:\n"
                                + "13; 8; 14; 9; 15"
                                + "\n \n \n",
                                "10",
                                "11",
                                "10",
                                "13",
                                "7"));
                //18
                db.addQuestion (new Question
                        ("Câu 18:\n "
                                + "Tìm số còn thiếu trong dãy số sau:\n"
                                + "1  4  9  16  ?"
                                + "\n \n \n",
                                "25",
                                "27",
                                "25",
                                "36",
                                "13"));
                //19
                db.addQuestion (new Question
                        ("Câu 19:\n "
                                + "Điền những số còn thiếu \n"
                                + "4; 12; 8; 24; 16; (…)"
                                + "\n \n \n",
                                "48",
                                "42",
                                "44",
                                "46",
                                "48"));
                //20
                db.addQuestion (new Question
                        ("Câu 20:\n "
                                + "Số tiếp theo của dãy số\n"
                                + "5 11 18 26 35 ?"
                                + "\n \n \n",
                                "45",
                                "45",
                                "50",
                                "55",
                                "60"));
                //21
                db.addQuestion (new Question
                        ("Câu 21:\n "
                                + "Ký tự tiếp theo trong dãy sau đây là ký tự nào:\n"
                                + "A…C…F…J…O… ?"
                                + "\n \n \n",
                                "U",
                                "S",
                                "T",
                                "U",
                                "V"));

            }
        } catch (Exception e) {
            //Toast toast = Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT);
            //toast.show();
        }


        // Reading all ques
        Log.d("Reading: ", "Reading all contacts..");
        List<Question> questionList = db.getAllQuestions ();

        for (Question ques : questionList) {
            String log = "Id: " + ques.getID () + " ,QUES: " + ques.getQuestion () + " ,ANS: " + ques.getAns ()
                    + " , AAA: " + ques.get_ansA ()
                    + " , BBB: " + ques.get_ansB ()
                    + " , CCC: " + ques.get_ansC ()
                    + " , DDD: " + ques.get_ansD ();
            // Writing Contacts to log
            Log.d("Name: ", log);
        }

        //New Player
        player01 = new Player ();
        //arrLog = new int[22];
        //for(int i = 0 ; i < 22; i++){
        //    arrLog[i] = -1;
        //}

        //Toast.makeText(PlayActivity.this, "da tao xong db", Toast.LENGTH_LONG).show();
        quesList = db.getAllQuestions();
        currentQ = quesList.get(qid);

        // gan view
        txtQuestion = (TextView) findViewById (R.id.txtQuestion);

        btnAns1 = (Button) findViewById (R.id.btnPlay1);
        btnAns2 = (Button) findViewById (R.id.btnPlay2);
        btnAns3 = (Button) findViewById (R.id.btnPlay3);
        btnAns4 = (Button) findViewById (R.id.btnPlay4);

        btnBack = (Button) findViewById (R.id.btnBack);
        btnNext = (Button) findViewById (R.id.btnNext);

        txtScore = (TextView) findViewById (R.id.txtScore);
        txtTime = (TextView) findViewById (R.id.txtTime);

        setQuestionView();
        txtTime.setText ("Timer: ...");
        txtScore.setText ("Score: 0");

        //CounterClass timer = new CounterClass (1800000, 1000);
        //timer.start ();

        countDownTimer = new CountDownTimer (1800000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                txtTime.setText ("Timer: " + millisUntilFinished / 1000 + " s");
            }

            @Override
            public void onFinish() {
                txtTime.setText ("Time is up !");
                Intent intent = new Intent (PlayActivity.this, ResultActivity.class);
                Bundle b = new Bundle ();
                b.putInt ("score", score);
                intent.putExtras (b);
                startActivity (intent);
                finish ();
            }
        };
        countDownTimer.start ();

        // check ans = btnClick
        btnAns1.setOnClickListener (this);
        btnAns2.setOnClickListener (this);
        btnAns3.setOnClickListener (this);
        btnAns4.setOnClickListener (this);

        btnBack.setOnClickListener (this);
        btnNext.setOnClickListener (this);
    }

    // kiem tra cau tra loi
    public void getAnswer(String AnsString){

        if(currentQ.getAns().equals(AnsString) == true){
            score++;
            txtScore.setText ("Score: " + score);
            //Toast.makeText(PlayActivity.this, "dap an dung", Toast.LENGTH_LONG).show();
        }else {
            //Toast.makeText(PlayActivity.this, "dap an sai", Toast.LENGTH_LONG).show();

            //Intent intent = new Intent (PlayActivity.this, ResultActivity.class);
        }

        if(qid > 0) {
            player01.setAnsPlayer_qid (qid - 1, AnsString);
            player01.setAnsCorrect_qid (qid - 1, currentQ.getAns ());
            player01.set_score (score);
        }

        if(qid < 20){
            currentQ = quesList.get (qid);
            setQuestionView();

        } else {

            String[] myStrings = new String[20];
            for(int i = 0 ; i < 20 ; i++){
                myStrings[i] = "Ques " + (i + 1) + ": " + "Your ans: " + player01.getStringArrAnsPlayer (i)
                + "\n    Correct answer: " + player01.getStringArrAnsCorrect (i);
            }
            //intent.putExtra("strings", myStrings);

            Intent intent = new Intent (PlayActivity.this, ResultActivity.class);
            Bundle b = new Bundle ();
            b.putInt ("score", score);
            intent.putExtra("strListHistory", myStrings);
            intent.putExtras (b);
            startActivity (intent);
            finish ();
        }
    }

    @Override
    public void finish() {
        txtTime.setText ("Time is up !");
    }


    public void onFinish() {
        txtTime.setText ("Time is up !");
    }

    private void setQuestionView(){

        /*txtQuestion.setText (currentQ.getQuestion ());
        btnAns1.setText (currentQ.get_ansA ());
        btnAns2.setText (currentQ.get_ansB ());
        btnAns3.setText (currentQ.get_ansC ());
        btnAns4.setText (currentQ.get_ansD ());
        qid++; */



        // neu cau hoi chua tra loi
        if(player01.getStringArrAnsPlayer (qid) == " "){

            txtQuestion.setText (currentQ.getQuestion ());
            btnAns1.setText (currentQ.get_ansA ());
            btnAns2.setText (currentQ.get_ansB ());
            btnAns3.setText (currentQ.get_ansC ());
            btnAns4.setText (currentQ.get_ansD ());

            btnAns1.setEnabled (true);
            btnAns2.setEnabled (true);
            btnAns3.setEnabled (true);
            btnAns4.setEnabled (true);

            qid++;

        } else{ // cau hoi da tra loi
            txtQuestion.setText (currentQ.getQuestion ());
            btnAns1.setText (currentQ.get_ansA ());
            btnAns2.setText (currentQ.get_ansB ());
            btnAns3.setText (currentQ.get_ansC ());
            btnAns4.setText (currentQ.get_ansD ());

            btnAns1.setEnabled (false);
            btnAns2.setEnabled (false);
            btnAns3.setEnabled (false);
            btnAns4.setEnabled (false);

            qid++;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPlay1:
                //player01.setAnsPlayer_qid (qid, btnAns1.getText().toString());
                getAnswer(btnAns1.getText().toString());
                break;

            case R.id.btnPlay2:
                //player01.setAnsPlayer_qid (qid, btnAns2.getText().toString());
                getAnswer(btnAns2.getText().toString());
                break;

            case R.id.btnPlay3:
                //player01.setAnsPlayer_qid (qid, btnAns3.getText().toString());
                getAnswer(btnAns3.getText().toString());
                break;

            case  R.id.btnPlay4:
                //player01.setAnsPlayer_qid (qid, btnAns4.getText().toString());
                getAnswer(btnAns4.getText().toString());
                break;

            case  R.id.btnBack:
                BackQues();
                break;

            case  R.id.btnNext:
                NextQues();
                break;
        }
    }

    private void BackQues() {
        if(qid >= 2) {
            qid = qid - 2;
            currentQ = quesList.get (qid);
            setQuestionView ();
        }
    }

    private void NextQues() {
        if(qid < 20) {
            currentQ = quesList.get (qid);
            setQuestionView ();
        }
    }
}
