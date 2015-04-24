/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package similarity;


import java.util.Scanner;
/**
 *
 * @author Mathialakan.Thavappi
 */
public class NumWord {


    public enum hundreds {onehundred, twohundred, threehundred, fourhundred, fivehundred, sixhundred, sevenhundred, eighthundred, ninehundred}
    public enum tens {twenty, thirty, forty, fifty, sixty, seventy, eighty, ninety}
    public enum ones {one, two, three, four, five, six, seven, eight, nine}
    public enum denom {thousand, lakhs, crores}
    public enum splNums { ten, eleven, twelve, thirteen, fourteen, fifteen, sixteen, seventeen, eighteen, nineteen}
    public  String text = "";

    public  String convert(int num) 
    {
        int rem = 0;
        int i = 0;
        while(num > 0)
        {
            if(i == 0){
                rem = (int) (num % 1000);
                printText(rem);
                num = num / 1000;
                i++;
            }
            else if(num > 0)
            {
                rem = (int) (num % 100);
                if(rem > 0)
                    text = denom.values()[i - 1] + text;
                printText(rem);
                num = num / 100;
                i++;
            }
        }
        if(i > 0)
            return text;
        else
            return "Zero";
    }

    public  void printText(int num)
    {
        if(!(num >= 9 && num <= 19))
        {
            if(num % 10 > 0)
                getOnes(num % 10);
             //System.out.println("num11 : " +num);
            num = num / 10;
            if(num % 10 > 0)
                getTens(num % 10);

            num = num / 10;
            if(num > 0)
                getHundreds(num);
        }
        else
        {
            getSplNums(num % 10);
        }
    }

    public  void getSplNums(int num)
    {
        text = splNums.values()[num]+ text;
    }

    public  void getHundreds(int num)
    {
        text = hundreds.values()[num - 1] + text;
    }

    public  void getTens(int num)
    {
        //System.out.println("num : " +num);
        text = tens.values()[num - 2] + text;
    }

    public  void getOnes(int num)
    {
        text = ones.values()[num - 1] + text;
    }
}