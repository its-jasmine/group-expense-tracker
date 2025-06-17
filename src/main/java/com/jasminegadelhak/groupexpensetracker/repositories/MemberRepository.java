package com.jasminegadelhak.groupexpensetracker.repositories;

import com.jasminegadelhak.groupexpensetracker.model.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends CrudRepository<Member, Long> {
    public List<Member> findAll();
}
