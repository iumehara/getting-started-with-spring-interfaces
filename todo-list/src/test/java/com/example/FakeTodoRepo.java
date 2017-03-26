package com.example;

import java.util.List;

public class FakeTodoRepo implements TodoRepo {

    public List<String> getAll_returnValue;

    @Override
    public List<String> getAll() {
        return getAll_returnValue;
    }
}
