package com.filrouge.gypsogest.web.resource;

import com.filrouge.gypsogest.domain.Returned;
import com.filrouge.gypsogest.exception.ResponseHandler;
import com.filrouge.gypsogest.service.ReturnedService;
import com.filrouge.gypsogest.web.vm.ReturnedRequestVM;
import com.filrouge.gypsogest.web.vm.ReturnedResponseVM;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/returneds")
@RequiredArgsConstructor
public class ReturnedResource {

    private final ReturnedService returnedService;

    @PostMapping
    public ResponseEntity<?> createReturned(@Valid @RequestBody ReturnedRequestVM returnedRequest) {
        Returned returned = returnedRequest.toReturned();
        returned = returnedService.saveReturned(returned);
        ReturnedResponseVM returnedResponse = ReturnedResponseVM.fromReturned(returned);
        return ResponseHandler.created(returnedResponse, "Returned created successfully.");
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReturnedById(@PathVariable Long id) {
        return returnedService.findReturnedById(id)
                .map(returned -> ResponseHandler.ok(ReturnedResponseVM.fromReturned(returned), "Returned found."))
                .orElse(ResponseHandler.notFound("Returned not found."));
    }

    @GetMapping
    public ResponseEntity<?> getAllReturneds() {
        List<Returned> returneds = returnedService.findAllReturneds();
        List<ReturnedResponseVM> returnedResponses = returneds.stream()
                .map(ReturnedResponseVM::fromReturned)
                .collect(Collectors.toList());
        return ResponseHandler.ok(returnedResponses, "All returneds retrieved successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReturned(@PathVariable Long id, @Valid @RequestBody ReturnedRequestVM returnedRequest) {
        Returned updatedReturned = returnedRequest.toReturned();
        Returned returned = returnedService.updateReturned(id, updatedReturned);
        ReturnedResponseVM returnedResponse = ReturnedResponseVM.fromReturned(returned);
        return ResponseHandler.ok(returnedResponse, "Returned updated successfully.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReturned(@PathVariable Long id) {
        returnedService.deleteReturned(id);
        return ResponseHandler.ok(null, "Returned deleted successfully.");
    }
}
