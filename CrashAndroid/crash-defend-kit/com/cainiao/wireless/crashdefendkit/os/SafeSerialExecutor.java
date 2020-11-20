package com.cainiao.wireless.crashdefendkit.os;

import android.os.AsyncTask;

import com.cainiao.wireless.crashdefendkit.CrashDefendKit;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;

public class SafeSerialExecutor implements Executor {

    final ArrayDeque<Runnable> mTasks = new ArrayDeque<Runnable>();
    Runnable mActive;

    public synchronized void execute(final Runnable r) {
        mTasks.offer(new Runnable() {
            public void run() {
                try {
                    r.run();
                }catch (Exception e){
                    CrashDefendKit.onCrash(SafeSerialExecutor.class, e);
                }finally {
                    scheduleNext();
                }
            }
        });
        if (mActive == null) {
            scheduleNext();
        }
    }

    protected synchronized void scheduleNext() {
        if ((mActive = mTasks.poll()) != null) {
            AsyncTask.THREAD_POOL_EXECUTOR.execute(mActive);
        }
    }
}
