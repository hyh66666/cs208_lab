public class test {
    public static void main(String[] args) {
        long a=recursive(3,3,true);
        long b=recursive1(3,3);
        System.out.println(a);
        System.out.println(b);
    }
    public static long recursive(long a,long exponent,boolean flag){
        long n = exponent;
        if(flag) {
            n = (long) Math.pow(2, exponent);
            flag= false;
        }
        if(n==0){
            return 1;
        }
        else if(n==1){
            return a;
        }
        else {
            return a*recursive( a,n-1,flag);
        }
    }
    public static long recursive1(int n,int a){
        if(n==1){
            return (long) a *a;
        }
        else {
            return recursive1(n-1,a)*recursive1(n-1,a);
        }
    }
}
