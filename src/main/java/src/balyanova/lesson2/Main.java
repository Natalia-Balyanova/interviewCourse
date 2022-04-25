package src.balyanova.lesson2;

public class Main {
    public static void main(String[] args) {
        MyList<String> linkedArr = new LinkedListImpl<>();
        linkedArr.add("A");//0
        linkedArr.add("B");//1
        linkedArr.add("C");//2
        linkedArr.add("D");//3
        System.out.println(linkedArr.isEmpty());//false
        System.out.println(linkedArr);//[LinkedListImpl{A, B, C, D]
        System.out.println(linkedArr.size());//4
        linkedArr.remove(3);
        System.out.println(linkedArr);//[LinkedListImpl{A, B, C]
        linkedArr.set(1, "X");
        System.out.println(linkedArr);//[LinkedListImpl{A, X, C]
        System.out.println(linkedArr.size());//3
        System.out.println(linkedArr.contains("B"));//false

        System.out.println("---------------------------------------");

        MyList<String> arrayList = new ArrayListImpl<>();
        arrayList.add("A");//0
        arrayList.add("B");//1
        arrayList.add("C");//2
        arrayList.add("D");//3
        System.out.println(arrayList);//ArrayListImpl{array=[A, B, C, D, null, null, null, null, null, null], size=4, capacity=10}
        arrayList.remove(2);
        arrayList.removeFirst();
        System.out.println(arrayList);//ArrayListImpl{array=[B, D, null, null, null, null, null, null, null, null], size=2, capacity=10}
        arrayList.set(0, "X");
        System.out.println(arrayList.size());//2
        System.out.println(arrayList.isEmpty());//false
        System.out.println(arrayList.contains("A"));//false
        System.out.println(arrayList.contains("X"));//true
    }
}