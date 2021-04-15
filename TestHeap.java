package Practice;

/**
 * 思路：从这棵树的最后一颗子树开始，进行向下调整
 */

public class TestHeap {
        public int[] elem;
        public int usedSize;

        public TestHeap() {
            this.elem = new int[10];
            this.usedSize = 0;
        }

        //首先创建一个简单的堆
        public void createHeap(int[] arr) {
            for (int i = 0; i < arr.length; i++) {
                this.elem[i] = arr[i];
                this.usedSize++;
            }
            //i代表每颗子树的根节点
            for (int i = (this.usedSize - 1 - 1) / 2; i >= 0; i--) {
                adjustDown(i, this.usedSize);
            }
        }

        //对这个堆进行向下调整，使其变为一个大根堆,传入要调整的根,和调整结束的位置
        public void adjustDown(int root, int len) {
            int parent = root;
            int child = 2 * parent + 1;
            //首先得判断左孩子的下标是否大于要调整的结束的位置
            while (child < len) {
                //1.找到左右孩子的最大值，但是首先得判断右孩子的下表是否大于要调整的结束位置，然后再找到最大值，进行交换
                if ((child + 1 < len) && this.elem[child] < this.elem[child + 1]) {
                    child++;//左右孩子的最大值
                }
                //此时判断左右孩子的最大值是否大于父亲节点，如果大于，则交换
                if (this.elem[child] > this.elem[parent]) {
                    int tmp = this.elem[child];
                    this.elem[child] = this.elem[parent];
                    this.elem[parent] = tmp;
                    //此时在让父亲节点指向孩子节点，再去调整，因为调整了一颗子树后可能会影响到其他子树
                    parent = child;
                    child = 2*child + 1;
                } else {
                    break;
                }
            }
        }

        //打印这个大根堆
        public void print() {
            for (int i = 0; i < this.usedSize; i++) {
                System.out.print(this.elem[i] + " ");
            }
        }


        public static void main(String[] args) {
            int[] arr = {27, 15, 19, 18, 28, 34, 65, 49, 25, 37};
            TestHeap testHeap = new TestHeap();
            testHeap.createHeap(arr);
            testHeap.print();
        }
    }
