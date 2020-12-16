| 排序算法  | 最差         | 平均     | 最好               | 稳定性 | 内外         |
| --------- | ------------ | -------- | ------------------ | ------ | ------------ |
| selection | O(n^2)       | O(n^2)   | O(n^2)             | 不     | 内           |
| insertion | O(n^2)       | O(n^2)   | O(n) (几乎正序)    | 是     | 内           |
| bubble    | O(n^2)       | O(n^2)   | O(n) (几乎正序)    | 是     | 内           |
| merge     | O(nlogn)     | O(nlogn) | O(nlogn)           | 是     | 外           |
| quick     | O(n^2)(有序) | O(nlogn) | O(nlogn)(等价归并) | 不     | 内(简单外)   |
| heap      | O(nlogn)     | O(nlogn) | O(nlogn)           | 不     | 内（简单外） |
| tree      | O(n^2)       | ?        | O(nlogn)           | ?      | ?            |


| 结构        | 插入      | 寻找      | 删除      | 前驱    | 后继    | 最大                  |
| ----------- | --------- | --------- | --------- | ------- | ------- | --------------------- |
| BST         | O(n)      | O(n)      | O(n)      | O(n)    | O(n)    | O(n)                  |
| **平衡**BST | O(logN)   | O(logN)   | O(logN)   | O(LogN) | O(LogN) | O(LogN)               |
| 二叉堆      | O(logN)   | O(logN)   | O(logN)   | ？      | ？      | O(1)取值，O(logN)删除 |
| hash        | O(1)-O(N) | O(1)-O(N) | O(1)-O(N) | O(N)?   | O(N)?   | O(N)?                 |

## Binary Tree
number of nodes in a tree of depth $k$ is between $k+1,2^{k+1}-1$   
number of *leaves* in a tree of height $h$ is at most $2^h$    
Depth of a tree of n nodes is between $\lfloor log_2n\rfloor,  n−1$   
Depth of a complete tree (heap) of n nodes is $\lfloor log_2n\rfloor$   
number of different Trees of k **levels**:  1, 1, 3, 21, 651, 457653, ...  
number of different Trees of k **nodes** :1, 1, 2, 5, 14, 42, 132, 429, 1430 (Catalan numbers)  
number of null reference of tree of n nodes: n+1  
n0=n2+1, N=n0+n1+n2  
n个节点二叉链表, 有n+1个空指针。 n-1 个非空指针

## 堆  
下标为k的节点，父节点:$\lceil \frac{k-1}{2} \rceil$ 左：2k+1 右：2k+2  
Number of (binary) heaps on n elements.  1, 1, 1, 2, 3, 8, 20, 80, 210, 896, 3360, 19200,