import java.util.*;

public class BinaryTree {

	//节点类
	private static class TreeNode {
		public int key;
		public TreeNode left;
		public TreeNode right;

		public TreeNode(int key) {
			this.key = key;
		}
	}

	TreeNode root;//当前二叉树的根节点

	public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.addNode(10);
		tree.addNode(7);
		tree.addNode(15);
		tree.addNode(3);
		tree.addNode(8);
		tree.addNode(11);
		tree.addNode(17);
		
		preorderTraversal1(tree.root);
        System.out.println("root : " + tree.root.key);	

        TreeNode dllHead = convertBst2Dll(tree.root);
        TreeNode focusNode = dllHead;
        System.out.println("dllHead : " + dllHead.key);
        while(focusNode != null) {
        	System.out.println(focusNode.key + " ");	
        	focusNode = focusNode.right;
        }
	}

	//向当前二叉树中增加节点(这里默认为了搜索二叉树，否则key就没有规则了)
	private void addNode(int key) {
		TreeNode newNode = new TreeNode(key);

		if (root == null) {
			root = newNode;	
		} else {
			TreeNode focusNode = root;
			TreeNode parent;

			while(true) {

				parent = focusNode;

				if (key < focusNode.key) {
					focusNode = focusNode.left;
					if (focusNode == null) {
						parent.left = newNode;
						return;
					}
				}else {
					focusNode = focusNode.right;
					if (focusNode == null) {
						parent.right = newNode;
						return;
					}
				}
			}
		}
	}

	//删除节点
	//成功删除返回true
	private boolean removeNode(int key) {
		if (root == null) {
			return false;
		}

		TreeNode focusNode = root;
		TreeNode parent = root;
		boolean isItAleft = true;

		while(focusNode.key != key) {//loop util find the key node
			if (key < focusNode.key) {
				isItAleft = true;
				focusNode = focusNode.left;
			} else {
				isItAleft = false;
				focusNode = focusNode.right;
			}

			if (focusNode == null) {//not found
				return false;
			}
		}

		//to here, that means we found the node
		if (focusNode.left == null && focusNode.right == null) {//the node does not have children
			
			if (focusNode == root) {
				root = null;
			}else if (isItAleft) {
				parent.left = null;
			}else {
				parent.right = null;
			}

		}
		return false;
		// todo here
	}

	//获取全部节点数
	//1. 递归实现
	private static int getNodesNum1(TreeNode rootNode) {
		if (rootNode == null) {
			return 0;
		}
		return getNodesNum1(rootNode.left) + getNodesNum1(rootNode.right) + 1;
	}
	//2. 循环实现
	private static int getNodesNum2(TreeNode rootNode) {
		if (rootNode == null) {
			return 0;
		}
		int sum = 0;//计数器
		LinkedList<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.addLast(rootNode);
		while(!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.removeFirst();//从队头取出节点
			sum++;//弹出一个节点计数器加1	
			if (node.left != null) {
				nodeQueue.addLast(node.left);
			}
			if (node.right != null) {
				nodeQueue.addLast(node.right);
			}	
		}
		return sum;
	}

	 /** 
     * 求二叉树的深度（高度） 递归解法： O(n) 
     * （1）如果二叉树为空，二叉树的深度为0  
     * （2）如果二叉树不为空，二叉树的深度 = max(左子树深度， 右子树深度) + 1 
     */  
	private static int getHeight1(TreeNode rootNode) {
		if (rootNode == null) {
			return 0;
		}
		int leftHeight = getHeight1(rootNode.left);
		int rightHeight = getHeight1(rootNode.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}
	private static int getHeight2(TreeNode rootNode) {
		if (rootNode == null) {
			return 0;
		}
		int height = 0;
		int curLevNodes = 1;//当前层的节点数
		int nextLevNodes = 0;//下一层的节点数
		LinkedList<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.addLast(rootNode);

		while(!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.removeFirst();
			curLevNodes--;//因为队头移除一个节点，所以当前层的节点数要减去1
			if (node.left != null) {
				nodeQueue.addLast(node.left);
				nextLevNodes++;//因为在队尾增加了一个当前节点的孩子节点，所以下一层节点数要加1
			}
			if (node.right != null) {
				nodeQueue.addLast(node.right);
				nextLevNodes++;//同上
			}

			if (curLevNodes == 0) {//当前层的节点数为0，即已经遍历完了当前层了，就要将二叉树高度加1，并且重置下一层节点数为当前层节点数
				height++;
				curLevNodes = nextLevNodes;
				nextLevNodes = 0;
			}
		}
		return height;
	}

	/** 
     * 前序遍历，中序遍历，后序遍历 前序遍历递归解法：  
     * （1）如果二叉树为空，空操作  
     * （2）如果二叉树不为空，访问根节点，前序遍历左子树，前序遍历右子树 
     */ 
	private static void preorderTraversal1(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}
		System.out.println(rootNode.key + " ");
		preorderTraversal1(rootNode.left);
		preorderTraversal1(rootNode.right);
	}

	/** 
     *  前序遍历迭代解法：用一个辅助stack，总是先把右孩子放进栈 
     *  http://www.youtube.com/watch?v=uPTCbdHSFg4 
     */  
	private static void preorderTraversal2(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}
		Stack<TreeNode> nodeStack = new Stack<>();//用栈的思想
		nodeStack.push(rootNode);//入栈
		while(!nodeStack.isEmpty()) {
			TreeNode node = nodeStack.pop();//出栈
			System.out.print(node.key + " ");
			// 关键点：要先压入右孩子，再压入左孩子，这样在出栈时会先打印左孩子再打印右孩子  
			if (node.right != null) {
				nodeStack.push(node.right);
			}
			if (node.left != null) {
				nodeStack.push(node.left);
			}
		}
	}

	 /** 
     * 中序遍历递归解法  
     * （1）如果二叉树为空，空操作。  
     * （2）如果二叉树不为空，中序遍历左子树，访问根节点，中序遍历右子树 
     */  
	private static void inorderTraversal1(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}
		inorderTraversal1(rootNode.left);
		System.out.println(rootNode.key + " ");
		inorderTraversal1(rootNode.right);
	}

	/** 
     * 中序遍历迭代解法 ，用栈先把根节点的所有左孩子都添加到栈内， 
     * 然后输出栈顶元素，再处理栈顶元素的右子树 
     * http://www.youtube.com/watch?v=50v1sJkjxoc 
     *  
     * 还有一种方法能不用递归和栈，基于线索二叉树的方法，较麻烦以后补上 
     * http://www.geeksforgeeks.org/inorder-tree-traversal-without-recursion-and-without-stack/ 
     */  
	private static void inorderTraversal2(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}
		Stack<TreeNode> nodeStack = new Stack<>();
		TreeNode curNode = rootNode;
		while(true) {
			if (curNode != null) {
				nodeStack.push(curNode);
				curNode = curNode.left;
			}else {
				if (nodeStack.isEmpty()) {
					break;
				}
				curNode = nodeStack.pop();
				System.out.println(curNode.key + "  ");
				curNode = curNode.right;
			}
		}
	}

	/** 
     * 后序遍历递归解法  
     * （1）如果二叉树为空，空操作  
     * （2）如果二叉树不为空，后序遍历左子树，后序遍历右子树，访问根节点 
     */ 
	private static void postorderTraversal1(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}
		postorderTraversal1(rootNode.left);
		postorderTraversal1(rootNode.right);
		System.out.println(rootNode.key + " ");
	}

	/** 
     *  后序遍历迭代解法 
     *  使用两个栈
     *  https://www.youtube.com/watch?v=qT65HltK2uE
     */ 
	private static void postorderTraversal2(TreeNode rootNode) {
		if (rootNode == null) {
			return;
		}
		Stack<TreeNode> stack1 = new Stack<>();
		Stack<TreeNode> stack2 = new Stack<>();
		TreeNode curNode = rootNode;
		stack1.push(curNode);
		while(!stack1.isEmpty()) {
			curNode = stack1.pop();
			stack2.push(curNode);
			if (curNode.left != null) {
				stack1.push(curNode.left);
			}
			if (curNode.right != null) {
				stack1.push(curNode.right);
			}
		}
		while(!stack2.isEmpty()) {
			System.out.println(stack2.pop().key + "  ");
		}
	}

	 /** 
     * 分层遍历二叉树（按层次从上往下，从左往右）迭代 
     * 相当于广度优先搜索，使用队列实现。队列初始化，将根节点压入队列。当队列不为空，进行如下操作：弹出一个节点 
     * ，访问，若左子节点或右子节点不为空，将其压入队列 
     */  
    public static void levelTraversal(TreeNode rootNode) {  
        if (rootNode == null) {  
            return;  
        }  
        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
        TreeNode focusNode = rootNode;
        nodeQueue.addLast(focusNode);
        while(!nodeQueue.isEmpty()) {
        	focusNode = nodeQueue.removeFirst();
        	System.out.println(focusNode.key + "  ");
        	if (focusNode.left != null) {
        		nodeQueue.addLast(focusNode.left);
        	}
        	if (focusNode.right != null) {
        		nodeQueue.addLast(focusNode.right);
        	}
        }
    }

    /**
     * 题目：输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
     * 要求不能创建任何新的结点，只能调整树中结点指针的指向。
     *
     * @param root 二叉树的根结点
     * @return 双向链表的头结点
     */
    public static TreeNode convertBst2Dll(TreeNode root) {
        //1.new一个数组用于保存处理过程中的双向链表的尾结点
        TreeNode[] lastNode = new TreeNode[1];
        
        //2.开始转换
        convertNode(root, lastNode);
        
        //3.转换结束，找到双向链表的头结点
        TreeNode head = lastNode[0];
        while (head != null && head.left != null) {
            head = head.left;
        }
        return head;
    }
    /**
     * 链表表转换操作
     *
     * @param node     当前的根结点
     * @param lastNode 已经处理好的双向链表的尾结点，使用一个长度为1的数组，类似C++中的二级指针
     */
    public static void convertNode(TreeNode node, TreeNode[] lastNode) {
        // 结点不为空
        if (node != null) {
            // 如果有左子树就先处理左子树
            if (node.left != null) {
                convertNode(node.left, lastNode);
            }
            
            // 将当前结点的前驱指向已经处理好的双向链表（由当前结点的左子树构成）的尾结点
            node.left = lastNode[0];
            // 如果左子树转换成的双向链表不为空，设置尾结点的后继
            if (lastNode[0] != null) {
                lastNode[0].right = node;
            }
            // 记录当前结点为尾结点
            lastNode[0] = node;

            // 处理右子树
            if (node.right != null) {
                convertNode(node.right, lastNode);
            }
        }
    }

}