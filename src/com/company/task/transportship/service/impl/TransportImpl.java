package com.company.task.transportship.service.impl;

import com.company.task.transportship.model.Product;
import com.company.task.transportship.model.Transport;
import com.company.task.transportship.service.TransportService;

import java.util.*;

public class TransportImpl implements TransportService {

    private static TransportImpl instance;

    private Integer costCount;

    private Integer weightCount;

    private TransportImpl() {
    }

    public static TransportImpl getInstance() {

        if (instance == null) {

            instance = new TransportImpl();
        }
        return instance;
    }

    @Override
    public String countProduct(Transport transport) {

        Map<Integer, Set<Product>> map = new HashMap<>();

        List<Product> productList = transport.getProducts();

        productList.sort((o1, o2) -> o2.getCost() - o1.getCost());

        for (int i = 0; i < productList.size() - 1; i++) {

            Set<Product> productSet = new HashSet<>();

            Product productInit = productList.get(i);

            productSet.add(productInit);

            initData(productInit);

            for (int j = i + 1; j < productList.size(); j++) {

                Product productNext = productList.get(j);

                weightCount = weightCount + productNext.getWeight();

                 checkFullWeight(transport,productNext,productSet,productInit,map);

            }

            map.put(costCount, productSet);
        }

        return getStringResult(map);

    }


    private Integer getMaxCost(Map<Integer, Set<Product>> map) {

        Integer max = 0;

        for (Map.Entry<Integer, Set<Product>> entry : map.entrySet()) {

            if (max < entry.getKey()) {
                max = entry.getKey();
            }
        }

        return max;
    }

    private String getStringResult(Map<Integer, Set<Product>> map) {

        StringBuilder stringBuilder = new StringBuilder();

        Integer max = getMaxCost(map);

        for (Product product : map.get(max)) {
            stringBuilder.append(product.getName()).append(" ");
        }
        return stringBuilder.toString() + max;
    }


    private void initData(Product product) {

        costCount = product.getCost();

        weightCount = product.getWeight();
    }

    private void checkFullWeight(Transport transport, Product next, Set<Product> productSet, Product init, Map<Integer, Set<Product>> map){

        if (weightCount <= transport.getCarrying()) {

            costCount = costCount + next.getCost();

            productSet.add(next);

            productSet.add(init);

            if(weightCount.equals(transport.getCarrying())){

                Set<Product> products = new HashSet<>(productSet);

                map.put(costCount,products);

                productSet.clear();

                costCount = init.getCost();

                weightCount = init.getWeight();
            }

        } else {
            weightCount = weightCount - next.getWeight();
        }
    }






}

