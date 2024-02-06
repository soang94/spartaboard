package org.example.spartaboard.common.querydsl

import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext

abstract class QueryDslSupport {

    @PersistenceContext // EntityManager 가 필요할때!
    protected lateinit var entityManager: EntityManager

    protected val queryFactory: JPAQueryFactory
        get() {
            return JPAQueryFactory(entityManager)
        }
}