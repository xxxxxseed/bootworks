package com.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.boot.entity.Guestbook;

public interface GuestbookRepository extends JpaRepository<Guestbook, Long>,
QuerydslPredicateExecutor<Guestbook>{

}
