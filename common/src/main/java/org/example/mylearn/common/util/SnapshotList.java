package org.example.mylearn.common.util;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class SnapshotList<T> {

    private final List<T> delegate;

    public SnapshotList(List<T> delegate) {
        this.delegate = delegate;
    }

    /* ================= 写操作 ================= */

    public void add(T element) {
        synchronized (delegate) {
            delegate.add(element);
        }
    }

    public void addLast(T element) {
        synchronized (delegate) {
            delegate.add(element);
        }
    }

    public boolean remove(T element) {
        synchronized (delegate) {
            return delegate.remove(element);
        }
    }

    public void clear() {
        synchronized (delegate) {
            delegate.clear();
        }
    }

    public int size(){
        synchronized (delegate) {
            return delegate.size();
        }
    }
    /** 弱一致 size（不加锁） */
    public int approxSize() {
        return delegate.size();
    }

    public List<T> subList( int fromIndex, int toIndex) {
        synchronized (delegate) {
            return delegate.subList(fromIndex, toIndex);
        }
    }

    /* ================= 读操作 ================= */

    /**
     * 获取一个快照（弱一致、只读安全）
     */
    public List<T> snapshot() {
        synchronized (delegate) {
            return new ArrayList<>(delegate);
        }
    }

    /**
     * 返回内部原始list，用于修改list等，caller需要自己处理锁
     * @return
     */
    public List<T> rawList() {
            return delegate;
    }

    /**
     * 遍历读，无锁
     * @param consumer
     */
    public void forEachSnap(Consumer<? super T> consumer) {
        List<T> snap = snapshot();
        for (int i = 0 ; i <= snap.size() - 1; i++) {
            consumer.accept(snap.get(i));
        }
    }
    /**
     * 倒序遍历快照
     */
    public void forEachReverseSnap(Consumer<? super T> consumer) {
        List<T> snapshot = snapshot();
        for (int i = snapshot.size() - 1; i >= 0; i--) {
            consumer.accept(snapshot.get(i));
        }
    }

    /**
     * 遍历写，必须有锁
     * @param consumer
     */
    public void forEachwithLock(Consumer<? super T> consumer) {
        synchronized (delegate) {
            for (int i = 0 ; i <= delegate.size() - 1; i++) {
                consumer.accept(delegate.get(i));
            }
        }
    }

}

