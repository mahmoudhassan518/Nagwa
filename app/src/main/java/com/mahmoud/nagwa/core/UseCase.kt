package com.mahmoud.nagwa.core

interface UseCase {

    interface WithParam<TYPE, PARAM> {
        fun invoke(param: PARAM): TYPE
    }

    interface NoParam<TYPE> {
        fun invoke(): TYPE
    }
}
