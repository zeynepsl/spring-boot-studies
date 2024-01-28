package com.zeynep.casestudy.model;

import java.util.List;

public record User(String username, String password, List<String> roles) {
}
