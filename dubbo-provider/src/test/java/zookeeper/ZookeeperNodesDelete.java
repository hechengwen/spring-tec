package zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * Copyright (C), 2017，jumore Tec.
 * Author: hechengwen
 * Version:
 * Date: 2018/4/28 8:55
 * Description:
 * Others:
 */
public class ZookeeperNodesDelete implements Watcher {
    //超时时间
    private static final int SESSION_TIMEOUT = 20000;

    private ZooKeeper zookeeper;
    private CountDownLatch connectedSignal = new CountDownLatch(1);

    /**
     * 连接zookeeper
     *
     * @param host
     * @throws Exception
     */
    public void connect(String host) throws Exception {
        zookeeper = new ZooKeeper(host, SESSION_TIMEOUT, this);
        connectedSignal.await();
        System.out.println("zookeeper connection success...");
    }

    /**
     * 关闭zookeeper连接
     *
     * @throws Exception
     */
    public void closezookeeper() throws Exception {
        zookeeper.close();
    }


    @Override
    public void process(WatchedEvent watchedEvent) {
        if (watchedEvent.getState() == Event.KeeperState.SyncConnected) {
            connectedSignal.countDown();
        }
    }

    /**
     * 获取路径下所有子节点
     *
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public List<String> getChildren(String path) throws KeeperException, InterruptedException {
        List<String> children = zookeeper.getChildren(path, false);
        System.out.println(path + ":节点下" + children);
        return children;
    }

    /**
     * 递归删除节点path和path下的所有子节点
     *
     * @param path
     * @throws KeeperException
     * @throws InterruptedException
     */
    public void delete(String path) throws KeeperException, InterruptedException {
        //看看传入的节点是否存在
        if (zookeeper.exists(path, false) != null) {
            //查看该节点下是否还有子节点
            List<String> list = zookeeper.getChildren(path, false);
            if (list.size() != 0) {
                //遍历子节点,递归调用自身的方法
                for (String child : list) {
                    delete(path + "/" + child);
                    System.out.println("目录结构："+ path + "/" + child);
                }
            }
            deleteNode(path);
            System.out.println("删除节点：" + path);
        }
    }

    /**
     * 获取节点上面的数据
     *
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getData(String path) throws KeeperException, InterruptedException {
        byte[] data = zookeeper.getData(path, false, null);
        if (data == null) {
            return "";
        }
        String result = new String(data);
        System.out.println(path + " 节点下有如下数据：" + result);
        return result;
    }

    /**
     * 删除节点
     *
     * @param path
     * @throws InterruptedException
     * @throws KeeperException
     */
    public void deleteNode(String path) throws InterruptedException, KeeperException {
        zookeeper.delete(path, -1);
    }

    /**
     * 设置节点信息
     *
     * @param path
     * @param data
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Stat setData(String path, String data) throws KeeperException, InterruptedException {
        Stat stat = zookeeper.setData(path, data.getBytes(), -1);
        return stat;
    }

    /**
     * 获取创建时间
     *
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public String getCTime(String path) throws KeeperException, InterruptedException {
        Stat stat = zookeeper.exists(path, false);
        Date date = new Date(stat.getCtime());
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }


    /**
     * 获取某个路径下孩子的数量
     *
     * @param path
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    public Integer getChildrenNum(String path) throws KeeperException, InterruptedException {
        int childenNum = zookeeper.getChildren(path, false).size();
        return childenNum;
    }


    /**
     * 创建zookeeper节点
     *
     * @param zNode
     * @throws Exception
     */
    public void createZNode(String zNode, String data) throws Exception {
        String path = "/" + zNode;
        String createPath = zookeeper.create(path, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(createPath);
    }


    public static void main(String[] args) {
        try {
            ZookeeperNodesDelete create = new ZookeeperNodesDelete();
            create.connect("192.168.184.130:2181");
//            create.createZNode("testZNode","helle world");

            create.getData("/dubbo");

//            create.getChildren("/dubbo");

//            create.delete("/dubbo");
            create.closezookeeper();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
