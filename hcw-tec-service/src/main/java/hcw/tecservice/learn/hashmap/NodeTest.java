package hcw.tecservice.learn.hashmap;

import java.util.Date;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/5/4 15:39
 * Description:
 * Others:
 */
public class NodeTest {

    /**
     * 静态内部类Node
     */
    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        Node head = null;
        if (in.hasNextInt()) {
            head = new Node(in.nextInt());
        }
        Node temp = head;
        while (in.hasNextInt()) {
            temp.next = new Node(in.nextInt());
            temp = temp.next;
        }
        in.close();*/
//        int len = getListLength(head);
//        Node reHead = reverseList(head);
//        reHead = reverseListRec(reHead);
//        Node node = getMiddleNode(head);
//        Node node = addNode(2, new Node(1));
//        deleteNode(3,head);
//        reGetKthNode(head, 3);
        Node node1 = new Node(1);
        Node node2 =new Node(2);
        Node node3 =new Node(3);
        Node node4 =new Node(4);
        Node node5 =new Node(5);
        Node node6 =new Node(6);
        node1.next = node5;
        node5.next = node3;
        node3.next = node6;
        node6.next = node2;
        node2.next = node4;
//        deleteNode(2,node1);
//        isCycle(node1);
//        addNodeByIndex(3,new Node(0),node1);
//        selectSort(node1);
//        bubbleSort(node1);
        Date date1 = new Date(2017,10,16);
        Date date2 = new Date(2018,05,11);
        int days = differentDaysByMillisecond(date1,date2);

    }
    public static int differentDaysByMillisecond(Date date1, Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * 对链表中元素进行排序(选择排序)
     * @param head
     */
    public static Node selectSort(Node head){
        if (head == null || head.next == null) return head;

        Node temp = head;
        while (temp != null) {
            Node next = temp.next;
            while (next != null) {
                if (temp.value > next.value) {
                    int i = temp.value;
                    temp.value = next.value;
                    next.value = i;
                }
                next = next.next;
            }
            temp = temp.next;
        }
        return head;
    }

    /**
     * 冒泡排序单链表
     * @param head
     * @return
     */
    public static Node  bubbleSort(Node head){
        if (head == null || head.next == null) return head;

        Node temp1 = head;
        while (temp1 != null) {
            Node temp = head;
            Node next = temp.next;
            while (next != null) {
                if (temp.value > next.value) {
                    int i = temp.value;
                    temp.value = next.value;
                    next.value = i;
                }
                temp = temp.next;
                next = next.next;
            }
            temp1 = temp1.next;
        }
        return head;
    }

    /**
     * 在链表末尾增加节点
     *
     * @param data
     */
    public static Node addNode(int data, Node head) {
        if (head == null) {
            head = new Node(data);
            return head;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = new Node(data);
        return temp;
    }

    public static Node addNodeByIndex(int index,Node m,Node head){
        if (index > getListLength(head) || index < 1) return null;
        Node pre = head;
        Node cur = head.next;
        int i = 1;
        while (cur != null) {
            if (index == i) {
                pre.next = m;
                m.next = cur;
                return head;
            }
            pre = pre.next;
            cur = cur.next;
            i++;
        }
        return head;
    }

    /**
     * 删除第index位置的节点
     *
     * @param index
     * @param head
     * @return
     */
    public static Node deleteNode(int index, Node head) {
        if (index < 0 || index > getListLength(head)) {
            return null;
        }
        if (index == 1) {
            head = head.next;
            return head;
        }

        Node pre = head;
        Node cur = head.next;
        int i = 2;
        while (cur != null) {
            if (i == index) {
                pre.next = cur.next;
                cur.next = null;
                return pre;
            }
            pre = pre.next;
            cur = cur.next;
            i++;
        }
        return head;

    }


    /**
     * 单链表中的节点个数
     *
     * @param head
     * @return
     */
    public static int getListLength(Node head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        System.out.println("链表长度为：" + len);
        return len;
    }

    /**
     * 将单链表反转，循环实现
     *
     * @param head
     * @return
     */
    public static Node reverseList(Node head) {
        if (head == null || head.next == null) return head;
        Node pre = null;
        Node next = null;
        while (head != null) {
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    /**
     * 将单链表反转,递归.有点看不懂。。。。
     *
     * @param head
     * @return
     */
    public static Node reverseListRec(Node head) {
        if (head == null || head.next == null) return head;
        Node reHead = reverseListRec(head.next);
        head.next.next = head;
        head.next = null;
        return reHead;
    }

    /**
     * 查找单链表的中间结点,快慢指针
     * 方法一、先求出链表的长度，再遍历1/2链表长度，寻找出链表的中间结点
     * 方法二、：
     * 用两个指针遍历链表，一个快指针、一个慢指针，
     * 快指针每次向前移动2个结点，慢指针一次向前移动一个结点，
     * 当快指针移动到链表的末尾，慢指针所在的位置即为中间结点所在的位置
     *
     * @param head
     * @return
     */
    public static Node getMiddleNode(Node head) {
        if (head == null || head.next == null) return head;
        Node target = head;
        Node temp = head;
        while (temp != null && temp.next != null) {
            target = target.next;
            temp = temp.next.next;
        }
        return target;
    }

    /**
     * 判断单链表是否有环
     * 设置快指针和慢指针，慢指针每次走一步，快指针每次走两步
     * 当快指针与慢指针相等时，就说明该链表有环
     *
     * @param head
     * @return
     */
    public static boolean isCycle(Node head) {
        if (head == null) return false;
        Node slow = head;
        Node fast = head;
        while (fast != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查找单链表中的倒数第K个结点（k > 0）
     *
     * @param head
     * @param k
     */
    public static Node reGetKthNode(Node head, int k) {
        if (head == null) return head;
        if (k > getListLength(head) || k < 0) return null;
        Node pre = head;
        Node cur = head;
        for (int i = 0; i < k; i++) {
            cur = cur.next;
        }
        while (cur != null) {
            pre = pre.next;
            cur = cur.next;
        }
        return pre;
    }

    /**
     * 判断两个单链表是否相交
     * 如果两个链表相交，则肯定有相同的尾节点，遍历两个链表，记录尾节点，看是否相同
     * @param head1
     * @param head2
     * @return
     */
    public static Node isCross(Node head1, Node head2) {
        if (head1 == null || head2 == null) return null;
        int len1 = getListLength(head1);
        int len2 = getListLength(head2);

        if (len1 > len2) {
            for (int i = 0; i < len1 - len2; i++) {
                head1 = head1.next;
            }
        } else {
            for (int i = 0; i < len2 - len1; i++) {
                head2 = head2.next;
            }
        }

        while (head1 != null && head2 != null) {
            if (head1 == head2) return head1;

            head1 = head1.next;
            head2 = head2.next;
        }
        return null;
    }
}
