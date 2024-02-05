package org.example.spartaboard.common.exception.dto

import org.example.spartaboard.common.status.ResultCode

data class BaseResponse<T> (
    val resultCode: String = ResultCode.SUCCESS.name,
    val date: T? = null,
    val message: String = ResultCode.SUCCESS.msg
)