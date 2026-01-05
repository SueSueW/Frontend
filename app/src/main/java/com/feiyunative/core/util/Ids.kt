package com.feiyunative.core.util

import java.util.UUID

fun newId(): String = UUID.randomUUID().toString()
fun nowMillis(): Long = System.currentTimeMillis()
