package com.filrouge.gypsogest.web.resource;
import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.exception.ResponseHandler;
import com.filrouge.gypsogest.service.ClientService;
import com.filrouge.gypsogest.web.vm.ClientRequestVM;
import com.filrouge.gypsogest.web.vm.ClientResponseVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientResource {

    private final ClientService clientService;

    @PostMapping
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientRequestVM clientRequest) {
        Client client = clientRequest.toClient();
        client = clientService.saveClient(client);
        ClientResponseVM clientResponse = ClientResponseVM.fromClient(client);
        return ResponseHandler.created(clientResponse, "Client created successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        return clientService.findClientById(id)
                .map(client -> ResponseHandler.ok(ClientResponseVM.fromClient(client), "Client found."))
                .orElse(ResponseHandler.notFound("Client not found."));
    }

    @GetMapping
    public ResponseEntity<?> getAllClients() {
        List<Client> clients = clientService.findAllClients();
        List<ClientResponseVM> clientResponses = clients.stream()
                .map(ClientResponseVM::fromClient)
                .collect(Collectors.toList());
        return ResponseHandler.ok(clientResponses, "All clients retrieved successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @Valid @RequestBody ClientRequestVM clientRequest) {
        Client updatedClient = clientRequest.toClient();
        Client client = clientService.updateClient(id, updatedClient);
        ClientResponseVM clientResponse = ClientResponseVM.fromClient(client);
        return ResponseHandler.ok(clientResponse, "Client updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
        return ResponseHandler.ok(null, "Client deleted successfully.");
    }
}
