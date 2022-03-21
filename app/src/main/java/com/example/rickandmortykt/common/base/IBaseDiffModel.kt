package com.example.rickandmortykt.common.base

interface IBaseDiffModel {
    val id: Int
    override fun equals(other: Any?): Boolean
}