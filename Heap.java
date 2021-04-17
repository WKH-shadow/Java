package TestDemo;

/**
 * 堆排序
 */

public class Heap {
    private int[] elem;
    private int usedSize;
    public Heap(){
        this.elem = new int[10];
        this.usedSize = 0;
    }
    //首先将数据变成一个大根堆,也就是创建一个大根堆
    public int[] createHeap(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            this.elem[i] = arr[i];
            this.usedSize++;
        }
        //要调整多少次
        for (int i = (this.usedSize-1-1)/2; i >= 0 ; i--) {
            adjustDown(i,this.usedSize);
        }
        return arr;
    }

    //然后从最后一个元素最后一个元素进行向下调整,传入要调整的节点，和结束的位置的节点下标
    private void adjustDown(int root, int len) {
        int parent = root;
        int child = 2*parent+1;
        //首先这个父亲节点的左孩子节点得小于要调整的结束的位置的下标
        while (child < len){
            //此时在判断有没有右孩子节点，并且找出左右孩子节点的最大值
            if((child+1) < len && this.elem[child] < this.elem[child+1]){
                //然后将左右孩子最大的那个存在child++下
                child++;
            }
            //此时让左右孩子最大的那个值和父亲节点进行比较，如果比父亲节点大就交换，反之则结束
            if(this.elem[parent] < this.elem[child]){
                int tmp = this.elem[parent];
                this.elem[parent] = this.elem[child];
                this.elem[child] = tmp;
                //然后再让父亲节点指向孩子节点，孩子节点指向此时父亲节点的孩子节点，来判断这个孩子是否有孩子节点，并且是不是大根堆
                //因为一个节点的调整可能会导致其他子树变成不是大根堆了
                parent = child;
                child = 2*parent+1;
            }else {
                break;
            }
        }
    }
    //打印这个大根堆
    public void print(){
        for (int i = 0; i < this.usedSize; i++) {
            System.out.print(this.elem[i] +" ");
        }
    }

    //然后对这个堆进行堆排序
    public void heapSort(){
        //首先定义一个end每次记录最后一个能够交换的下标
        int end = this.usedSize-1;
        //当end > 0的时候;然后那第一个元素和end下标的元素进行交换
        while (end > 0){
            int tmp = this.elem[0];
            this.elem[0] = this.elem[end];
            this.elem[end] = tmp;
            //因为最后一个元素已经有序了，那么就需要将剩下的0~end的位置进行调整使其变成一个大根堆
            //为什么是到end呢？而不是到end-1，这需要追溯到我们adjustDown中结束是child<len，其中不包括len，所以这里的end也就不包括end
            adjustDown(0,end);
            //然后end--指向当前大根堆的最后一个元素
            end--;
        }
    }
    //top-k问题优化版本
    public static void main(String[] args) {
        int[] arr = {27,15,19,18,28,34,65,49,25,37};
        Heap heap = new Heap();
        int[] array = heap.createHeap(arr);
        heap.print();
        System.out.println();
        heap.heapSort();
        heap.print();
    }
}
