package concurrent.juc_test.utils.exchange;

import java.util.concurrent.SynchronousQueue;

/**
 * 自己实现 Exchanger 思路 维护一个 SynchronousQueue
 *
 * 当有exchange 执行一个判断当前有无阻塞
 */
public class MyExchanger<V> {

    SynchronousQueue<V> synchronousQueue = new SynchronousQueue<>();

    public V exchange(V v) throws InterruptedException {
        V res = synchronousQueue.poll();
        synchronousQueue.put(v);

        if (res == null) {
            try {
                res = synchronousQueue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

}
