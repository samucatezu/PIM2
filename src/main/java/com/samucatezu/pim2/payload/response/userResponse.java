package com.samucatezu.pim2.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class userResponse {
//  private String token;
//  private String type = "Bearer";
//  private Long id;
//
//  private String username;
//  private String email;
//  private List<String> roles;

  @JsonProperty("")
  private UserValor user;

//  public JwtResponse(String accessToken, Long id, String username, String email, List<String> roles, UserValor user) {
//    this.token = accessToken;
//    this.id = id;
//    this.username = username;
//    this.email = email;
//    this.roles = roles;
//    this.user = user;
//  }

  public userResponse(UserValor user) {
    this.user = user;
  }

}
