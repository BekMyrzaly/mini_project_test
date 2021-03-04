package com.codecademy.unquote;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions;


    ImageView questionImageView;
    TextView questionTextView;
    TextView questionsRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.activity_main);

        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        questionsRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
        answer0Button = findViewById(R.id.btn_main_answer_0);
        answer1Button = findViewById(R.id.btn_main_answer_1);
        answer2Button = findViewById(R.id.btn_main_answer_2);
        answer3Button = findViewById(R.id.btn_main_answer_3);
        submitButton = findViewById(R.id.btn_main_submit_answer);

        answer0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(0);
            }
        });

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(1);
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(2);
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(3);
            }
        });

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSubmission();
            }
        });
        startNewGame();
    }


    public void displayQuestion(Question question) {
        questionImageView.setImageResource(question.imageId);
        questionTextView.setText(question.questionText);
        answer0Button.setText(question.answer0);
        answer1Button.setText(question.answer1);
        answer2Button.setText(question.answer2);
        answer3Button.setText(question.answer3);
    }

    public void displayQuestionsRemaining(int questionsRemaining) {
        questionsRemainingTextView.setText(String.valueOf(questionsRemaining));
    }

    public void onAnswerSelected(int answerSelected) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelected;
        answer0Button.setText(currentQuestion.answer0);
        answer1Button.setText(currentQuestion.answer1);
        answer2Button.setText(currentQuestion.answer2);
        answer3Button.setText(currentQuestion.answer3);


        switch(answerSelected) {
            case 0:
                answer0Button.setText("✔ " + currentQuestion.answer0);
                break;
            case 1:
                answer1Button.setText("✔ " + currentQuestion.answer1);
                break;
            case 2:
                answer2Button.setText("✔ " + currentQuestion.answer2);
                break;
            case 3:
                answer3Button.setText("✔ " + currentQuestion.answer3);
                break;
        }
    }
    public void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrect()) {
            totalCorrect = totalCorrect + 1;
        }

        if(currentQuestion.playerAnswer == -1) {
            return;
        }

        questions.remove(currentQuestion);


        displayQuestionsRemaining(questions.size());

        if (questions.size() == 0) {
            String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);


            AlertDialog.Builder gameOverDialogBuilder = new AlertDialog.Builder(MainActivity.this);
            gameOverDialogBuilder.setCancelable(false);
            gameOverDialogBuilder.setTitle("Game over!");
            gameOverDialogBuilder.setMessage(gameOverMessage);
            gameOverDialogBuilder.setPositiveButton("Play Again!", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startNewGame();
                }
            });
            gameOverDialogBuilder.create().show();
        } else {
            chooseNewQuestion();


            displayQuestion(getCurrentQuestion());
        }
    }

    public void startNewGame() {
        questions = new ArrayList<>();


        Question question0 = new Question(R.drawable.img_quote_0, "Адамзат тарихы дамуының ең алғашқы кезеңі", "Қола дәуірі", "Темір дәуірі", "Тас дәуірі", "Андронов кезеңі", 2);


        Question question1 = new Question(R.drawable.img_quote_1, "Алғашқы адамдардың бастапқы кезеңдегі топтасу жүйесі", "Тобыр", "Рулық", "Тайпалық", "Қауымдастық", 0);


        Question question2 = new Question(R.drawable.img_quote_2, "Алағашқы адамдардың тобырдан кейінгі топтасу жүйесі", "Тайпалық", "Рулық", "Өндірістік ұжым", "Малшылар қауымдастығы", 1);

        Question question3 = new Question(R.drawable.img_quote_3, "Алғашқы адамдардың рулық қауымнан кейінгі қалыптасу жүйесі", "аталық ру", "аналық ру", "терімшілер", "тайпа", 3);

        Question question4 = new Question(R.drawable.img_quote_4, "Қоғамда алғашқы ірі еңбек бөлінісін туғызған жағдай", "терімшіліктің дамуы", "шаруашылықтың егіншілік пен мал шаруашылығы болып бөлінуі", "аңшылықтың тууы", "тобырдың қалыптасуы.", 1);

        Question question5 = new Question(R.drawable.img_quote_5, "Ғалымдардың ең ежелгі адамды атауы:", "епті адам", "кроманьон", "неандерталь", "синантроп", 0);

        Question question6  = new Question(R.drawable.img_quote_6, "Ең ежелгі \"епті адамның\"  мөлшермен өмір сүрген мерзімі.", "500-200 мың жыл бұрын", "1 млн жыл бұрын", "1 млн. 750 мың жыл бұрын", "100-35 мың жыл бұрын", 2);

        Question question7 = new Question(R.drawable.img_quote_7, "Ең ежелгі адамның еңбек құралы", "Найза", "Бумеранг", "Садақ", "Үшкір тас", 3);

        Question question8 = new Question(R.drawable.img_quote_8, "Ежелгі \"Тік жүретін адам\" өкілі", "\"Епті адам\"", "Синантроп", "Неандертальдық", "\"Саналы адам\"", 1);

        Question question9 = new Question(R.drawable.img_quote_9, "Жер бетінде бұдан 100-35 мың жыл бұрын өмір сүрді:", "Неандертальдықтар", "\"Епті адамдар\"", "\"Саналы адамдар\"", "Синантроптар", 0);

        Question question10 = new Question(R.drawable.img_quote_10, "Жер бетінде 40-35мың жыл бұрын өмір сүрген адам:", "Питекантроп", "\"Епті адам\"", "\"Тік жүретін адам\"", "\"Саналы адам\"", 3);

        Question question11 = new Question(R.drawable.img_quote_11, "Ежелгі адамдардың ең алғашқы кәсібі", "Терімшілік", "Егіншілік", "Аң аулау мен балық аулап күнелту", "Мал шаруашылығы", 0);

        Question question12 = new Question(R.drawable.img_quote_12, "Тас дәуірін (палеолит) қамтитын кезең", "б.з.д. 100-75 ғасырлар", "б.з.д.5-3 мың жыл бұрын", "б.з.д. 2 млн 500 мың-12 мың жыл", "б.з.д. 12-5 мың жыл", 2);

        Question question13 = new Question(R.drawable.img_quote_0, "Орта тас ғасыры қамтитын кезең", " б.з.б. 5-3 мың жыл бұрын", "б.з.д.7-5 мың жыл", "б.з.б.12-5 мың жыл", "б.з.б.1 млн. 750 мың жыл бұрын", 2);

        Question question14 = new Question(R.drawable.img_quote_1, "Жаңа тас ғасыры қамтитын кезең", "б.з.б.5-3 мың жыл", "б.з.д. 3-2 мың жыл", " б.з.д.7-5 мың жыл", "б.з.д.12-5 мың жыл", 0);


        questions.add(question0);
        questions.add(question1);
        questions.add(question2);
        questions.add(question3);
        questions.add(question4);
        questions.add(question5);
        questions.add(question6);
        questions.add(question7);
        questions.add(question8);
        questions.add(question9);
        questions.add(question10);
        questions.add(question11);
        questions.add(question12);
        questions.add(question13);
        questions.add(question14);


        totalCorrect = 0;
        totalQuestions = questions.size();

        while(questions.size() > 15) {
            int randomIndex =  generateRandomNumber(questions.size());
            questions.remove(randomIndex);
        }

        Question firstQuestion = chooseNewQuestion();


        displayQuestionsRemaining(questions.size());


        displayQuestion(firstQuestion);
    }

    Question chooseNewQuestion() {
        int newQuestionIndex = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionIndex;
        return questions.get(currentQuestionIndex);
    }

    int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    Question getCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        return currentQuestion;
    }

    String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            return "You got all " + totalQuestions + " right! You won!";
        } else {
            return "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
        }
    }
}
