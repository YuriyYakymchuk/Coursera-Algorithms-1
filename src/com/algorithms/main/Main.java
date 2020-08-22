package com.algorithms.main;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");
    }

    private static int[] numsSameConsecDiff(int N, int K) {

        List<Integer> ans = new ArrayList<>();
        findAll(0,N,K,-1,ans);

        return ans.stream().mapToInt(Integer::intValue).toArray();
    }

    private static void findAll(int index, int n, int k, int cur,
                               List<Integer> ans){

        if(index == n){
            ans.add(cur);
            return;
        }
        if(cur == 0 && n>1)
            return;

        if(cur == -1){
            for(int i=0;i<=9;i++){
                findAll(index+1,n,k,i,ans);
            }
        }
        else{
            int rem = cur % 10;
            if(rem + k <=9)
                findAll(index+1,n,k,cur*10+(rem+k),ans);
            if(k!=0 && rem - k >= 0)
                findAll(index+1,n,k,cur*10+(rem-k),ans);
        }

    }
}
