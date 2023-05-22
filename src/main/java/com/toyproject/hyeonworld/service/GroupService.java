package com.toyproject.hyeonworld.service;

import com.toyproject.hyeonworld.entity.Group;
import com.toyproject.hyeonworld.repository.GameRepository;
import com.toyproject.hyeonworld.repository.GroupRepository;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public void init(Group group) {
        groupRepository.save(group);
    }
}
