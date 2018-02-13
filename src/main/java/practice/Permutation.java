package practice;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harshitha.suresh on 31/01/2018.
 */
public class Permutation {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(4);
        numbers.add(9);

        Permutation p = new Permutation();
        List<List<Integer>> allCombinations = p.findAllCombinations(numbers);
        System.out.println("args = [" + allCombinations + "]");
    }

    private List<List<Integer>> findAllCombinations(List<Integer> numbers) {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> copiedNumbers = new ArrayList<>();
        copiedNumbers.addAll(numbers);
        if (numbers.isEmpty()) {
            combinations.add(new ArrayList<>());
            return combinations;
        }
        combinations.add(copiedNumbers);
        Integer first = copiedNumbers.get(0);
        List<List<Integer>> answerFirstPart = findAllCombinations(copiedNumbers.subList(1, copiedNumbers.size()));


        List<List<Integer>> answerSecondPart = prefixFirstElement(first, answerFirstPart);

        return concat(answerFirstPart, answerSecondPart);
    }

    private List<List<Integer>> concat(List<List<Integer>> answerFirstPart, List<List<Integer>> answerSecondPart) {
        List<List<Integer>> copiedList = new ArrayList<>();
        copiedList.addAll(answerFirstPart);
        copiedList.addAll(answerSecondPart);
        return copiedList;
    }

    private List<List<Integer>> prefixFirstElement(Integer first, List<List<Integer>> answerFirstPart) {
        List<List<Integer>> prefixedList = new ArrayList<>();
        for (List<Integer> l: answerFirstPart) {
            List<Integer> tempList = new ArrayList<>();
            tempList.add(first);
            tempList.addAll(l);
            prefixedList.add(tempList);
        }
        return prefixedList;
    }
}
