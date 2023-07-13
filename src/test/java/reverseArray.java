public class reverseArray {
    public static void main(String[] args){
        String inputs = "praveen";
        char temps;
        char[] val = inputs.toCharArray();
        for(int i=0;i<val.length;i++){
            for(int j=i+1;j<val.length;j++){
                if(val[i]>val[j]){
                    temps=val[i];
                    val[i]=val[j];
                    val[j]=temps;
                }
            }
        }
        for (char vals:val) {
            System.out.print(vals);
        }
        System.out.println();

        int temp;
        int[] input = new int[]{1,2,5,3,7,4,1,100,99};
        int len = input.length-1;
        for(int i=0;i<len/2;i++){
            temp=input[i];
            input[i]=input[len-i];
            input[len-i]=temp;
        }
        for (int value:input) {
            System.out.println(value);

        }
        System.out.println("=---------");

        int n=4;
        int count=1;
        for(int i=0;i<n;i++){
            for(int j=0;j<n-i;j++){
                System.out.print(count+" ");
            }
            count++;
            System.out.println();
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=i;j++){
                System.out.print(j+" ");
            }
            System.out.println();
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n-i;j++) {
                System.out.print(" ");
            }
                for(int j=1;j<=2*i-1;j++){
                    System.out.print("*");
                }
            System.out.println();
            }

        }
    }

