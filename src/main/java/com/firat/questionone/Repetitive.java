package com.firat.questionone;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Repetitive {

    private String text;

    public Repetitive(String text){
        this.text = text;
    }

    public void topMostWords()
    {
        String output = "";
        Map<String,Long> wordsWithCounts = null;
        List<String> candidateToBeGroupedBy = convert();
        if (candidateToBeGroupedBy!=null && !candidateToBeGroupedBy.isEmpty())
            wordsWithCounts = groupBy(candidateToBeGroupedBy);

        if (wordsWithCounts!=null)
        {
            Long maxVal = findMaxValue(wordsWithCounts);
            Set<String> setMostUsedWords = findMostUsedWords(wordsWithCounts,maxVal);
            String rawMostUsedWords = toRawString(setMostUsedWords);
            if (setMostUsedWords.size()> 1)
            {
                output = String.format("En çok tekrar eden kelimeler %d adet ile << %s >> ", maxVal,rawMostUsedWords);
                System.out.println(output);
                return;
            }
            if (setMostUsedWords.size() == 1)
            {
                output = String.format("En çok tekrar eden kelime %d adet ile << %s >>",maxVal,rawMostUsedWords);
                System.out.println(output);
                return;
            }
            System.out.println("HAYDAAAA !!!!");
        }
        else
            System.out.println("No words !");
    }

    private List<String> convert()
    {
        return Stream.of(this.text.split(" ")).collect(Collectors.toList());
    }
    private Map<String, Long> groupBy(List<String> slicedString)
    {
        Map<String,Long> wordCount =
                slicedString
                        .stream()
                        .collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
        return wordCount;
    }
    private Long findMaxValue(Map<String,Long> input)
    {
        return input.entrySet().stream().max((value1,value2) -> value1.getValue() > value2.getValue()?1:-1).get().getValue();
    }
    private Set<String> findMostUsedWords(Map<String,Long> input, Long maxValue)
    {
        return input.entrySet().stream().filter(myMap -> myMap.getValue() == maxValue).collect(Collectors.toMap(myMap->myMap.getKey(),myMap->myMap.getValue())).keySet();
        //get0.getkey
    }
    private String toRawString(Set<String> input)
    {
        return String.join(", ",input);
    }

}
