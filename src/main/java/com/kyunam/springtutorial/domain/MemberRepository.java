package com.kyunam.springtutorial.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends CrudRepository<Member, Long> {

}
