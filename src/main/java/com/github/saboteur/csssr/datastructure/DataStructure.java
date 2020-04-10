package com.github.saboteur.csssr.datastructure;

public interface DataStructure {

    void insert(String item);

    default void parse(String inputString) {
        if (inputString == null)
            throw new IllegalArgumentException("Null key provided");

        String[] words =
            inputString.length() > 0
                ? inputString.split("\\s+")
                : new String[0];

        try {
            for (var word : words) {
                if (word == null || "".equals(word))
                    continue;

                this.insert(word);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
