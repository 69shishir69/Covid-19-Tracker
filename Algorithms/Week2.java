package prac1;

public class Week2 {
	static int maxProfit(int a[]){
        int profit=0;
        for(int i=1; i<a.length;i++){
            if(a[i]>a[i-1]){
                profit=profit+(a[i]-a[i-1]);
            }
        }
        return profit;
    }
    public static void main(String[] args) {
        int[] a= {100,180,260,310,40,535,695};
//        int[] a= {10000,1800,760,310,40,35,5};
        System.out.println(maxProfit(a));
    }
}
