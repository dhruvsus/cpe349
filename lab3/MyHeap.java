public class MyHeap{
	int heapArray[];
	int size;
	int capacity;

	public MyHeap(){
		this.size=0;
		this.capacity=50;
		//default capacity is 50
		this.heapArray=new int[51];
	}
	public MyHeap(int initCapacity){
		this.size=0;
		this.capacity=initCapacity;
		this.heapArray=new int[initCapacity+1];
	}
	public Boolean buildHeap(int[] A){
		//if it fits it sits
		if(A.length>capacity){
			return false;
		}
		else{
			//satisfy shape property
			System.arraycopy(A,0,heapArray,1,A.length);
			size=A.length;
			for(int i=size/2;i>=1;i--){
				driftDown(i);
			}
			return true;
		}
		//copy array from A to heapArray
	}
	public int getHeapCap(){
		return this.capacity;
	}
	public int getHeapSize(){
		return this.size;
	}
	public Boolean isEmpty(){
		if(size==0){
			return true;
		}
		return false;
	}
	public Boolean isFull(){
		if(size==capacity){
			return true;
		}
		return false;
	}
	public int deleteMin(){
		//delete and return root
		int min=heapArray[1];
		heapArray[1]=heapArray[size];
		size--;
		//drift root down to restore parental dominance
		driftDown(1);
		return min;
	}
	public Boolean insert(int toInsert){
		if(size==capacity){
			return false;
		}
		else{
			heapArray[++size]=toInsert;
			driftUp(size);
			return true;
		}
	}
	public void driftDown(int index){
		int tmp=heapArray[index];
		while(index*2<=size){
			int child=index*2;
			//if there exists another child which is smaller
			if(child!=size && (heapArray[child+1]<heapArray[child])){
				child++;
			}
			//find the smaller child
			// child is smaller than parent
			if(heapArray[child]<tmp){
				heapArray[index]=heapArray[child];//move child up
				index=child;//promise to move parent down
			}
			else{
				break;
			}
		}
		//put parent in it's proper place restoring parental dominance
		heapArray[index]=tmp;
	}
	public int findMin(){
		return heapArray[1];
	}
	public void driftUp(int index){
		int tmp=heapArray[index];
		//swap child with parent if it's smaller than the parent
		while(index>1&& tmp<heapArray[index/2]){
			heapArray[index]=heapArray[index/2];
			index=index/2;
		}
		heapArray[index]=tmp;
	}

	public static int[] heapSortDecreasing(int[] A){
		int[] sorted=new int[A.length];
		//build a heap out of heapArray
		MyHeap h=new MyHeap(A.length);
		h.buildHeap(A);
		//root removal through deleteMin
		for(int i=0;i<A.length;i++){
			int deleted=h.deleteMin();
			sorted[sorted.length-1-i]=deleted;
		}
		return sorted;
	}
/*
	public static void main(String[] args){
		//MyHeap h =new MyHeap(101);
		int[] testArray=new int[100];
		for(int i=0;i<100;i++){
			testArray[i]=i;
		}
		//build test heap
		//h.buildHeap(testArray);
		//insert
		//System.out.println("insert "+h.insert(101));
		//System.out.println("insert 2 "+h.insert(102));
		//System.out.println("delete min "+h.deleteMin());
		//System.out.println("capapcity "+h.getHeapCap());
		//System.out.println("size "+h.getHeapSize());
		testArray=MyHeap.heapSortDecreasing(testArray);
		for(int i=0;i<100;i++){
			System.out.println(testArray[i]);
		}
	}
*/
}
