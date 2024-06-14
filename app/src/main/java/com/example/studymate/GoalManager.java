package com.example.studymate;

import java.util.ArrayList;
import java.util.List;

public class GoalManager{
    private List<Goal> goals;
    private static GoalManager instance;

    private GoalManager(){
        goals=new ArrayList<>();
    }

    public static GoalManager getInstance(){
        if (instance== null) {
            instance=new GoalManager();
        }
        return instance;
    }
    public void addGoal(Goal goal){
        goals.add(goal);
    }

    public void removeGoal(Goal goal){
        goals.remove(goal);
    }

    public List<Goal> getGoals(){
        return goals;
    }
    public int getCompletedGoalsCount(){
        int count=0;
        for (Goal goal : goals){
            if (goal.isCompleted()){
                count++;
            }
        }
        return count;
    }
}
