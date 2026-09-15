package com.example.demo.service;

import com.example.demo.dto.request.RegisterRequest;
import com.example.demo.dto.response.Result;

public interface AuthService {

    public Result<Void> register(RegisterRequest request);

    public void generateId() throws Exception;

}
