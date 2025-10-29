package com.evalDanh.evalDanh.controllers;

import com.evalDanh.evalDanh.dao.ClientDao;
import com.evalDanh.evalDanh.models.Client;
import com.evalDanh.evalDanh.security.IsUser;
import com.evalDanh.evalDanh.views.ClientView;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/client")
@RequiredArgsConstructor
@CrossOrigin
public class ClientController {

    @Autowired
    protected ClientDao clientDao;

    @GetMapping("/list")
    @IsUser
    @JsonView(ClientView.class)
    public List<Client> show() {
        return clientDao.findAll();
    }
}
