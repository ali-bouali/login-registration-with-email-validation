package com.bouali.register.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {


  @GetMapping
  public String something() {
    return "Some text";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminOnly() {
    return "<h1>Admin access only</h1>";
  }

  @GetMapping("/user")
  @PreAuthorize("hasRole('USER')")
  public String userOnly() {
    return "<h1>User access only</h1>";
  }

  @GetMapping("/user-admin")
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  public String adminUser() {
    return "<h1>User / Admin access only</h1>";
  }


}
