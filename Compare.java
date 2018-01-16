package br.com.renato.androidloadjar;

public class Compare implements Comparable<String>{

    @Override
    public int compareTo(String o) {
        return new Test().compareTo("asdfasdf");
    }
}
