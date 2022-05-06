package com.example.simplequiz;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class QuizActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView Question;
    TextView TotalQuestion;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm;
    String rightAnswer;
    String Answer;
    List<QuestionList> questions;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        TotalQuestion=findViewById(R.id.totalquestion);
        confirm = findViewById(R.id.confirm);
        Question = findViewById(R.id.Question);
        optionA = findViewById(R.id.optionA);
        optionB = findViewById(R.id.optionB);
        optionC = findViewById(R.id.optionC);
        optionD = findViewById(R.id.optionD);
        score = 0;
        radioGroup = findViewById(R.id.radioGroup);
        questions = new ArrayList<QuestionList>(){
            {
                add(new QuestionList("The home of Gargi, Maitrey, and Kapila was at", "D", "Vidisha", "Ujjain","Pataliputra", "Mithila"));
                add(new QuestionList("Which area of India was known as Avantika in ancient times ?", "D", "Avadh", "Ruhelkhand","Bundelkhand", "Malwa"));
                add(new QuestionList("The Social System of the Harappans was :", "A", "Fairly egalitarian", "Slave-Labour based","Colour (Varna) based", "Caste based"));
                add(new QuestionList("Which of the following Vedas provides information about the civilisation of the Early Vedic Age?", "A", "Rig-veda", "Yajur-veda", "Atharva-veda", "Sama-veda"));
                add(new QuestionList("The university which became famous in the post-Gupta Era was :", "C", "Kanchi", "Taxila", "Nalanda", "Vallabhi"));
                add(new QuestionList("Banabhatta was the court poet of which emperor ?", "C", "Vikramaditya", "Kumaragupta", "Harshavardhana", "Kanishka"));
                add(new QuestionList("The first Indian ruler, who established the supremacy of Indian Navy in the Arabian Sea was :", "A", "Rajaraja I", "Rajendra I", "Rajadhiraja I", "Kulottunga I"));
                add(new QuestionList("The First Tirthankara of the Jains was :", "D", "Arishtanemi", "Parshvanath", "Ajitanath", "Rishabha"));
                add(new QuestionList("The most important text of vedic mathematics is :", "C", "Satapatha Brahman", "Atharva Veda", "Sulva Sutras", "Chhandogya Upanishad"));
                add(new QuestionList("Where did Lord Buddha breathe his last?", "D", "Rajgir", "Bodh Gaya", "Sarnath", "Kushinagar"));
            }
        };

        loadQuestion();
    }


    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }

    private void loadQuestion(){

        TotalQuestion.setText(0+1+"/"+questions.size());

        if(questions.size() > 0) {

            QuestionList q = questions.remove(0);
            Question.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
        } else {
            Intent intent = new Intent(this, QuizResults.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.optionA:
                Answer="A";
                break;

            case R.id.optionB:
                Answer="B";
                break;

            case R.id.optionC:
                Answer="C";
                break;

            case R.id.optionD:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        this.startActivity(isRightOrWrong(Answer));

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, TopicsPage.class);

        }else {
            screen = new Intent(this, Wrong.class);
        }

        return screen;
    }
}