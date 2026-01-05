package com.feiyunative.service

import android.content.Context
import android.content.Intent

object FocusTimerIntents {

    fun start(context: Context, planItemId: String): Intent =
        Intent(context, FocusTimerService::class.java).apply {
            action = FocusTimerService.ACTION_START
            putExtra(FocusTimerService.EXTRA_PLAN_ITEM_ID, planItemId)
        }

    fun stop(context: Context): Intent =
        Intent(context, FocusTimerService::class.java).apply {
            action = FocusTimerService.ACTION_STOP
        }
}
