package com.example.studymate;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private LinearLayout goalLayout;
    private ProgressBar progressBar;
    private TextView progressText;
    private TextView userIdTextView;
    private String userId;
    private User userInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        goalLayout=findViewById(R.id.goalLayout);
        progressBar=findViewById(R.id.progressBar);
        progressText=findViewById(R.id.progressText);
        userIdTextView=findViewById(R.id.userIdTextView);
        ImageButton buttonAdd=findViewById(R.id.buttonAdd);
        userId=getIntent().getStringExtra("USER_ID");
        userInfo=UserManager.getInstance().getUser(userId);

        if (userId!=null) {
            userIdTextView.setText("사용자 ID: "+ userId);
            userIdTextView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    Intent intent=new Intent(MainActivity.this, UserProfileActivity.class);
                    intent.putExtra("USER_ID", userId);
                    startActivity(intent);
                }
            });
        }
        buttonAdd.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showAddGoalDialog();
            }
        });
        updateUI();
    }

    private void showAddGoalDialog(){
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("새 목표 추가");
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("추가", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which){
                String goalName=input.getText().toString();
                if (!goalName.isEmpty()){
                    Goal goal= new Goal(goalName);
                    GoalManager.getInstance().addGoal(goal);
                    addGoalToUI(goal);
                    updateProgress();
                }
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void addGoalToUI(Goal goal){
        LinearLayout goalItemLayout =new LinearLayout(this);
        goalItemLayout.setOrientation(LinearLayout.HORIZONTAL);
        goalItemLayout.setPadding(0, 10, 0, 10);
        CheckBox goalCheckBox = new CheckBox(this);
        goalCheckBox.setText(goal.getName());
        goalCheckBox.setChecked(goal.isCompleted());
        goalCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            goal.setCompleted(isChecked);
            updateProgress();
        });

        TextView goalNameTextView = new TextView(this);
        goalNameTextView.setText(goal.getName());
        goalNameTextView.setPadding(10, 0, 0, 0);
        goalNameTextView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showGoalOptionsDialog(goal, goalCheckBox, goalNameTextView);
            }
        });

        goalItemLayout.addView(goalCheckBox);
        goalItemLayout.addView(goalNameTextView);
        goalLayout.addView(goalItemLayout);
    }

    private void showGoalOptionsDialog(Goal goal, CheckBox goalCheckBox, TextView goalNameTextView){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("옵션 선택");
        String[] options= {"이름 수정", "삭제"};
        builder.setItems(options, new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                switch(which){
                    case 0:
                        showEditGoalDialog(goal, goalCheckBox, goalNameTextView);
                        break;
                    case 1:
                        GoalManager.getInstance().removeGoal(goal);
                        goalLayout.removeView((View) goalCheckBox.getParent());
                        updateProgress();
                        break;
                }
            }
        });
        builder.show();
    }
    private void showEditGoalDialog(Goal goal, CheckBox goalCheckBox,TextView goalNameTextView){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("목표 이름 수정");
        final EditText input=new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        input.setText(goal.getName());
        builder.setView(input);
        builder.setPositiveButton("수정", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog,int which) {
                String newGoalName =input.getText().toString();
                if(!newGoalName.isEmpty()) {
                    goal.setName(newGoalName);
                    goalCheckBox.setText(newGoalName);
                    goalNameTextView.setText(newGoalName);
                }
            }
        });
        builder.setNegativeButton("취소",new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.cancel();
            }
        });
        builder.show();
    }

    private void updateProgress(){
        int totalGoals = GoalManager.getInstance().getGoals().size();
        int completedGoals = GoalManager.getInstance().getCompletedGoalsCount();
        int progress=(totalGoals == 0) ? 0 : (completedGoals * 100 / totalGoals);
        progressBar.setProgress(progress);
        progressText.setText("진행률: "+progress+ "%");
    }

    private void updateUI(){
        List<Goal> goals=GoalManager.getInstance().getGoals();
        for (Goal goal : goals){
            addGoalToUI(goal);
        }
        updateProgress();
    }
}
