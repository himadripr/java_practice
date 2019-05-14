package org.himadri.practice.java_practice.java_examples;

//https://www.interviewbit.com/problems/square-root-of-integer/

public class SquareRoot {
    public int sqrt(int aa) {
        long a = aa;
        long l=1, r=1;
        while ((r*r)<a){
            l=r;
            r=2*r;
        }
        while (l<=r){
            long mid = l+((r-l)/2);
            long sqr = mid*mid;
            if (sqr==a){
                return (int)mid;
            }
            if (sqr>a){
                if ((mid-1)*(mid-1)<=a){
                    return (int)(mid-1);
                } else {
                    r=mid;
                }
            } else {
                long v = (mid+1)*(mid+1);
                if (v>=a){
                    if (v>a)
                        return (int)mid;
                    else 
                        return (int)(mid+1);
                } else {
                    l=mid;
                }
            }
        }
        return -1;
    }
}
