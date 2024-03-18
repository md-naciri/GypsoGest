package com.filrouge.gypsogest.service.implementation;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Transaction;
import com.filrouge.gypsogest.exception.CustomException;
import com.filrouge.gypsogest.repository.TransactionRepo;
import com.filrouge.gypsogest.service.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TransactionServiceImpTest {

    @Mock
    private TransactionRepo transactionRepository;
    @Mock
    private ClientService clientService;
    private TransactionServiceImp transactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        transactionService = new TransactionServiceImp(transactionRepository, clientService);
    }

    @Test
    void saveTransaction_Successful() {
        // Test successful saving of transaction
        Transaction transaction = new Transaction();
        transaction.setPaymentCode("123");
        Client client = new Client();
        client.setId(1L);
        transaction.setClient(client);

        when(transactionRepository.findByPaymentCode("123")).thenReturn(Optional.empty());
        when(clientService.findClientById(1L)).thenReturn(Optional.of(client));
        when(transactionRepository.save(transaction)).thenReturn(transaction);

        Transaction savedTransaction = transactionService.saveTransaction(transaction);
        assertNotNull(savedTransaction);
        assertEquals("123", savedTransaction.getPaymentCode());
        assertEquals(client, savedTransaction.getClient());
    }

    @Test
    void saveTransaction_AlreadyExists() {
        // Test saving of transaction with existing payment code
        Transaction transaction = new Transaction();
        transaction.setPaymentCode("123");

        when(transactionRepository.findByPaymentCode("123")).thenReturn(Optional.of(transaction));

        assertThrows(CustomException.class, () -> transactionService.saveTransaction(transaction));
    }

    @Test
    void findTransactionById_Successful() {
        // Test successful finding of transaction by ID
        Transaction transaction = new Transaction();
        transaction.setId(1L);

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction));

        Optional<Transaction> foundTransaction = transactionService.findTransactionById(1L);
        assertTrue(foundTransaction.isPresent());
        assertEquals(transaction, foundTransaction.get());
    }

    @Test
    void findTransactionById_NotFound() {
        // Test finding of transaction by non-existent ID
        when(transactionRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Transaction> foundTransaction = transactionService.findTransactionById(1L);
        assertFalse(foundTransaction.isPresent());
    }

    @Test
    void findTransactionsByClientId_Successful() {
        // Test successful finding of transactions by client ID
        Client client = new Client();
        client.setId(1L);
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        transaction1.setClient(client);
        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        transaction2.setClient(client);
        Set<Transaction> transactions = new HashSet<>(Arrays.asList(transaction1, transaction2));

        when(clientService.findClientById(1L)).thenReturn(Optional.of(client));
        when(transactionRepository.findByClient_Id(1L)).thenReturn(transactions);

        Set<Transaction> foundTransactions = transactionService.findTransactionsByClientId(1L);
        assertEquals(transactions.size(), foundTransactions.size());
        assertTrue(foundTransactions.contains(transaction1));
        assertTrue(foundTransactions.contains(transaction2));
    }

    @Test
    void findTransactionsByClientId_ClientNotFound() {
        // Test finding of transactions by non-existent client ID
        when(clientService.findClientById(1L)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> transactionService.findTransactionsByClientId(1L));
    }

    @Test
    void findAllTransactions() {
        // Test finding all transactions
        Transaction transaction1 = new Transaction();
        transaction1.setId(1L);
        Transaction transaction2 = new Transaction();
        transaction2.setId(2L);
        List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

        when(transactionRepository.findAll()).thenReturn(transactions);

        List<Transaction> foundTransactions = transactionService.findAllTransactions();
        assertEquals(transactions.size(), foundTransactions.size());
        assertTrue(foundTransactions.contains(transaction1));
        assertTrue(foundTransactions.contains(transaction2));
    }

    @Test
    void updateTransaction() {
        // Test updating of transaction
        Long id = 1L;
        Transaction existingTransaction = new Transaction();
        existingTransaction.setId(id);
        Transaction updatedTransaction = new Transaction();
        updatedTransaction.setId(id);
        updatedTransaction.setAmount(100.0);
        // Set a non-null client
        Client client = new Client();
        client.setId(1L);
        updatedTransaction.setClient(client);

        when(transactionRepository.findById(id)).thenReturn(Optional.of(existingTransaction));
        when(clientService.findClientById(anyLong())).thenReturn(Optional.of(new Client()));
        when(transactionRepository.save(any(Transaction.class))).thenReturn(updatedTransaction);

        Transaction result = transactionService.updateTransaction(id, updatedTransaction);
        assertEquals(updatedTransaction.getAmount(), result.getAmount());
    }


    @Test
    void updateTransaction_NotFound() {
        // Test updating of non-existent transaction
        Long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> transactionService.updateTransaction(id, new Transaction()));
    }

    @Test
    void deleteTransaction() {
        // Test deleting of transaction
        Long id = 1L;
        Transaction transaction = new Transaction();
        transaction.setId(id);

        when(transactionRepository.findById(id)).thenReturn(Optional.of(transaction));

        transactionService.deleteTransaction(id);
        verify(transactionRepository, times(1)).delete(transaction);
    }

    @Test
    void deleteTransaction_NotFound() {
        // Test deleting of non-existent transaction
        Long id = 1L;
        when(transactionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> transactionService.deleteTransaction(id));
    }
}
