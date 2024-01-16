package com.filrouge.gypsogest.service.implementation;

import com.filrouge.gypsogest.domain.Client;
import com.filrouge.gypsogest.domain.Item;
import com.filrouge.gypsogest.domain.Sale;
import com.filrouge.gypsogest.exception.CustomException;
import com.filrouge.gypsogest.repository.ItemRepo;
import com.filrouge.gypsogest.repository.SaleRepo;
import com.filrouge.gypsogest.service.ClientService;
import com.filrouge.gypsogest.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SaleServiceImp implements SaleService {
    private final SaleRepo saleRepository;
    private final ClientService clientService;
    private final ItemRepo itemRepository;
    @Override
    @Transactional
    public Sale createSale(Sale sale) {
        // Ensure that the client exists
        Client client = clientService.findClientById(sale.getClient().getId())
                .orElseThrow(() -> new CustomException("Client not found with id: " + sale.getClient().getId()));
        sale.setClient(client);
        return saleRepository.save(sale);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Sale> findSaleById(Long id) {
        return saleRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Sale> findAllSales() {
        return saleRepository.findAll();
    }

    @Override
    @Transactional
    public Sale updateSale(Long id, Sale updatedSale) {
        Sale existingSale = saleRepository.findById(id)
                .orElseThrow(() -> new CustomException("Sale not found with id: " + id));

        Client client = clientService.findClientById(updatedSale.getClient().getId())
                .orElseThrow(() -> new CustomException("Client not found with id: " + updatedSale.getClient().getId()));

        existingSale.setDate(updatedSale.getDate());
        existingSale.setClient(client);
        itemRepository.deleteAll(existingSale.getItems());
        updatedSale.getItems().forEach(item -> item.setSale(existingSale));
        Set<Item> items = new HashSet<>(itemRepository.saveAll(updatedSale.getItems()));
        existingSale.setItems(items);
        return saleRepository.save(existingSale);
    }


    @Override
    @Transactional
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}
