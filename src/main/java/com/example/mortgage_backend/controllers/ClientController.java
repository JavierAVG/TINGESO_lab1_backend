package com.example.mortgage_backend.controllers;

import com.example.mortgage_backend.entities.ClientEntity;
import com.example.mortgage_backend.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/client")
public class ClientController {
    @Autowired
    ClientService clientService;

    @PostMapping("/")
    public ResponseEntity<ClientEntity> saveClient(@RequestBody ClientEntity client) {
        ClientEntity new_client = clientService.saveClient(client);
        return ResponseEntity.ok(new_client);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteClientById(@PathVariable Long id) throws Exception {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<ArrayList<ClientEntity>> getClients() {
        ArrayList<ClientEntity> client_list = clientService.getClients();
        return ResponseEntity.ok(client_list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientEntity> getClientById(@PathVariable Long id) {
        ClientEntity client = clientService.getClientById(id);
        return ResponseEntity.ok(client);
    }
}