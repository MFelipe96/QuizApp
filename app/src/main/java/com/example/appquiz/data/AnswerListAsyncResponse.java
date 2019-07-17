package com.example.appquiz.data;

import com.example.appquiz.model.Question;

import java.util.ArrayList;

public interface AnswerListAsyncResponse {

    void ProcessFinished(ArrayList<Question> questionArrayList);
}
