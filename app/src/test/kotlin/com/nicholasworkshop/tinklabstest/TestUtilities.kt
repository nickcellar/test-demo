package com.nicholasworkshop.tinklabstest

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins.setInitMainThreadSchedulerHandler
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins.*
import java.util.concurrent.Executor

object TestUtilities {

    fun initRxSchedulers() {
        val immediate = object : Scheduler() {
            override fun createWorker(): Scheduler.Worker {
                return ExecutorScheduler.ExecutorWorker(Executor { it.run() })
            }
        }
        setInitIoSchedulerHandler { immediate }
        setInitComputationSchedulerHandler { immediate }
        setInitNewThreadSchedulerHandler { immediate }
        setInitSingleSchedulerHandler { immediate }
        setInitMainThreadSchedulerHandler { immediate }
    }
}
