package com.example.mortgage_backend.services;

import com.example.mortgage_backend.entities.ClientEntity;
import com.example.mortgage_backend.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class ClientServiceTest {
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private ClientService clientService;
    private ClientEntity client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = new ClientEntity();
        client.setId(1L);
        client.setName("Matias Pino");
        client.setRut("98.765.432-1");
    }

    @Test
    void whenSaveClient_thenCorrect() {
        // Given
        client.setName("Juan Diaz");
        client.setRut("12.345.678-9");

        when(clientRepository.save(client)).thenReturn(client);

        // When
        ClientEntity returnedClient = clientService.saveClient(client);

        // Then
        assertThat(returnedClient.getName()).isEqualTo("Juan Diaz");
        assertThat(returnedClient.getRut()).isEqualTo("12.345.678-9");
    }

    @Test
    void whenGetClientById_thenCorrect() {
        // Given
        Long clientId = 1L;

        when(clientRepository.findById(clientId)).thenReturn(Optional.of(client));

        // When
        ClientEntity returnedClient = clientService.getClientById(clientId);

        // Then
        assertThat(returnedClient).isNotNull();
        assertThat(returnedClient.getId()).isEqualTo(1L);
        assertThat(returnedClient.getName()).isEqualTo(client.getName());
        assertThat(returnedClient.getRut()).isEqualTo(client.getRut());
    }

    @Test
    void whenGetClients_thenCorrect() {
        // Given
        ClientEntity client1 = new ClientEntity();
        client1.setId(1L);
        client1.setName("Juan Diaz");
        client1.setRut("12.345.678-9");

        ClientEntity client2 = new ClientEntity();
        client2.setId(2L);
        client2.setName("Maria Gonzalez");
        client2.setRut("98.765.432-1");

        List<ClientEntity> mockClientList = new ArrayList<>();
        mockClientList.add(client1);
        mockClientList.add(client2);
        // Mock
        when(clientRepository.findAll()).thenReturn(mockClientList);

        // When
        ArrayList<ClientEntity> returnedClients = clientService.getClients();

        // Then
        assertThat(returnedClients).isNotNull();
        assertThat(returnedClients.size()).isEqualTo(2);
        assertThat(returnedClients.get(0).getName()).isEqualTo("Juan Diaz");
        assertThat(returnedClients.get(0).getRut()).isEqualTo("12.345.678-9");
        assertThat(returnedClients.get(1).getName()).isEqualTo("Maria Gonzalez");
        assertThat(returnedClients.get(1).getRut()).isEqualTo("98.765.432-1");
    }

    @Test
    void whenDeleteClient_thenCorrect() throws Exception {
        // Given
        Long clientId = 1L;
        // Simulate the behavior of clientRepository.deleteById() without throwing an exception
        doNothing().when(clientRepository).deleteById(clientId);

        // When
        boolean isDeleted = clientService.deleteClient(clientId);

        // Then
        assertThat(isDeleted).isTrue();
        verify(clientRepository).deleteById(clientId); // Verifies that deleteById was called
    }

    @Test
    void whenDeleteClientFails_thenExceptionIsThrown() throws Exception {
        // Given
        Long clientId = 1L;

        // Simulate clientRepository.deleteById() throwing an exception
        doThrow(new RuntimeException("Deletion failed")).when(clientRepository).deleteById(clientId);

        // When/Then
        assertThatThrownBy(() -> clientService.deleteClient(clientId))
                .isInstanceOf(Exception.class)
                .hasMessageContaining("Deletion failed");
    }
}
