package JavaPractice;

/**
 * 玩扑克
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Card{
    public String suit;//花色，花色有【"♥","♠","♣","♦"】
    public int rank;//牌的数值，【1-13】

    //并提供其构造方法
    public Card(String suit, int rank) {
        this.suit = suit;
        this.rank = rank;
    }

    @Override
    public String toString() {
        return String.format("%s %d",suit,rank);
    }
}
//牌52：1 2 3 4 5 6 7 8 9 10 11 12 13
//花色："♥","♠","♣","♦"
public class TestDemo4 {
    public static final String[] suits = {"♥","♠","♣","♦"};//定义牌的花色,因为花色是不变额
    public static List<Card> buyCard(){
        List<Card> list = new ArrayList<>();//存放牌，买的牌存放在list
        for (int i = 0; i < 4; i++) {
            for (int j = 1; j <= 13; j++) {
                String suit = suits[i];//0->♥,1->♠,2->♣,3->♦
                int rank = j;//1-13
                Card card = new Card(suit,rank);
                list.add(card);//将买的牌放在list当中
            }
        }
        return list;
    }

    /**
     *这里有一个犯错的点，我们以前在发生两个数值交换的时候总是定义一个临时变量
     * 如交换整型的a和b  {int x = a,a = b,b = x};
     * 而在这里是面向对象，是方法，使用我们应该调用方法来实现交换操作
     * 也就是使用list中的set方法，
     */
    private static void swap(List<Card> list,int index,int i){
        Card tmp = list.get(index);//先保存index下标所对应的值
        list.set(index,list.get(i));//然后使用set方法将index位置的值修改为i下标的值
        list.set(i,tmp);//然后将i下标的值修改为保存在index位置的值也即tmp
    }
    //洗牌
    public static void shuffleCard(List<Card> list){  //买回来的牌存放在list当中，就直接传入list
        Random random = new Random();
        /*
        注意：这里的i=list.size-1，最后一张牌开始遍历的，
        虽然有52张牌，然是在数组中它的下标是51，这是一个容易犯错的地方
         */
        for (int i = list.size()-1; i > 0 ; i--) {
            int index = random.nextInt(i);//产生随机数[0,51)
            swap(list,index,i);
        }

    }

    public static void main(String[] args) {
        List<Card> ret = buyCard();
        System.out.println("这是买回来的牌"+"\n"+ret);
        shuffleCard(ret);
        System.out.println("洗完之后的牌"+"\n"+ret);

        /**
         * 揭牌操作
         * hands1 hands2 hands3 分别代表三个人，然后每个人揭的牌将会放入他们里面
         * 我们将三个人揭的牌放在lists中
         */
        List<List<Card>> lists = new ArrayList<>();
        List<Card> hands1 = new ArrayList<>();
        List<Card> hands2 = new ArrayList<>();
        List<Card> hands3 = new ArrayList<>();
        lists.add(hands1);
        lists.add(hands2);
        lists.add(hands3);

        for (int i = 0; i < 5; i++) { //每个人揭五张牌
            for (int j = 0; j < 3; j++) {  //一共有三个人
                lists.get(j).add(ret.remove(0));
            }
        }
        System.out.println("===========================");
        System.out.println("第一个人揭的牌");
        System.out.println(lists.get(0));
        System.out.println("第二个人揭的牌");
        System.out.println(lists.get(1));
        System.out.println("第三个人揭的牌");
        System.out.println(lists.get(2));
        System.out.println("剩下的牌"+"\n"+ret);
    }
}
