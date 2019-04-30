package com.company.task.transportship.controller;


import com.company.task.transportship.model.Product;
import com.company.task.transportship.model.Transport;
import com.company.task.transportship.service.TransportService;
import com.company.task.transportship.service.impl.TransportImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TransportController implements TransportHandler {

    private static final TransportService transportService = TransportImpl.getInstance();

    private static final String DELIMITER_PRODUCT = " ";

    private static final String DELIMITER_PROPERTY_PRODUCT = "/";

    private static final int NAME_PRODUCT = 0;

    private static final int WEIGHT_PRODUCT = 1;

    private static final int COST_PRODUCT = 2;

    private static final String MESSAGE_ERROR = "This operation is not possible";

    @Override
    public String getResult(String data, Transport transport) {
        return result(data,transport);
    }

    private String result(String data,Transport transport){
        try {
            String[] products = data.split(DELIMITER_PRODUCT);

            List<Product> productsModel = new ArrayList<>();

            for (String productString : products) {

                String[] property = productString.split(DELIMITER_PROPERTY_PRODUCT);

                Product product = new Product();

                product.setId(String.valueOf(UUID.randomUUID()));
                product.setName(property[NAME_PRODUCT]);
                product.setWeight(Integer.valueOf(property[WEIGHT_PRODUCT]));
                product.setCost(Integer.valueOf(property[COST_PRODUCT]));

                productsModel.add(product);
            }

            transport.setProducts(productsModel);

            return transportService.countProduct(transport);

        }catch (Exception e){
            return MESSAGE_ERROR;
        }
    }
}
