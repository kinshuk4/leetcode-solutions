// smallest element greater than or equal to key
class temp {

}

public static int binarySearchCeiling(int A[], int l, int h, int key){
int mid = (l+h)/2;

if(A[l] >= key){
return l;
}
if(A[h] < key ){
return -1;
}

if(A[mid] == key){
return mid;
}
//mid is greater than key, so either mid is the ceil or it exists in A[l..mid-1]
else if(A[mid] > key){
if(mid-1 >= l && A[mid-1] <= key){
return mid;
}
else{
return binarySearchCeiling(A, l, mid-1, key);
}
}
//mid is less than the key, so either mid+1 is the ceil or it exists in A[mid+1...h]
else{
if(mid + 1 <= h && A[mid+1] >= key){
return mid+1;
}
else{
return binarySearchCeiling(A, mid+1, h, key);
}
}
}