package com.coffeecorner.service.impl;

import com.coffeecorner.common.Constants;
import com.coffeecorner.common.Loggers;
import com.coffeecorner.domain.Product;
import com.coffeecorner.service.SelectionService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectionServiceImpl implements SelectionService {

    public void printMenu(Map<Integer, Product> products) {
        Loggers.log("===================================");
        Loggers.log("Welcome to Charlene's Coffee Corner");
        Loggers.log("===================================");
        Loggers.log("Offering");
        Loggers.log("===================================");
        Loggers.log("[0] EXIT menu");
        Loggers.log("===================================");
        products.forEach((k, p) -> Loggers.log(("[" + k + "] " + p)));
    }

    public List<Integer> readSelections(Set<Integer> availableSelections) {
        List<Integer> selections = null;
        int i = 0;

        while (selections == null && i < Constants.MAX_RETRIES) {
            Loggers.log("\nIntroduce products numbers separated by ',' without blanks and press ENTER key (Example: 1,3,4). Available selections are: ");
            availableSelections.forEach(result -> Loggers.log(result + " "));
            Loggers.log("\nYour selection: ");

            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                selections = Stream.of(bufferRead.readLine().split(Constants.COMMA)).map (Integer::valueOf).collect(Collectors.toList());
            } catch (Exception e) {
                Loggers.log("Wrong format. Please follow requested pattern. Examples are: 1,3,4 or 3,6,8 or ...");
                i++;
            }
        }
        return selections;
    }
}
