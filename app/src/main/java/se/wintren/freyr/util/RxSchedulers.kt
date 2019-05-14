package se.wintren.freyr.util

import io.reactivex.Scheduler

interface RxSchedulers {
    fun computation(): Scheduler
    fun io(): Scheduler
    fun mainThread(): Scheduler
}