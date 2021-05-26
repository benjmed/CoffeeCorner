package com.coffeecorner.service;

import com.coffeecorner.common.Constants;
import com.coffeecorner.domain.Product;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SelectionServiceImpl implements SelectionService{
    public void printMenu(Map<Integer, Product> products) {
        System.out.println("===================================");
        System.out.println("Welcome to Charlene's Coffee Corner");
        System.out.println("===================================");
        System.out.println("Offering");
        System.out.println("--------");
        System.out.println("[0] EXIT menu");
        System.out.println("········ ····");
        products.forEach((k, p) -> System.out.println(("[" + k + "] " + p)));
    }


    public List<Integer> readSelections(Set<Integer> availableSelections) {
        List<Integer> selections = null;
        int i = 0;

        while (selections == null && i < Constants.MAX_RETRIES) {
            System.out.print("\nIntroduce products numbers separated by ',' without blanks and press ENTER key (Example: 1,3,4). Available selections are: ");
            availableSelections.forEach(result -> System.out.print(result + " "));
            System.out.print("\nYour selection: ");

            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                selections = Stream.of(bufferRead.readLine().split(Constants.COMMA)).map (Integer::valueOf).collect(Collectors.toList());
            } catch (Exception e) {
                System.out.print("Wrong format. Please follow requested pattern. Examples are: 1,3,4 or 3,6,8 or ...");
                i++;
            }
        }
        return selections;
    }
}
