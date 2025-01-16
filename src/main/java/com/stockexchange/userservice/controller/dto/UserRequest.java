package com.stockexchange.userservice.controller.dto;

public record UserRequest(String username, String password, Boolean isAdmin) {}
