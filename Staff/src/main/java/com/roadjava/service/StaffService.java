package com.roadjava.service;

import com.roadjava.entity.StaffDO;
import com.roadjava.req.StaffRequest;
import com.roadjava.res.TableDTO;

public interface StaffService {

    TableDTO retrieveStaff(StaffRequest request);

    boolean add(StaffDO staffDO);

    StaffDO getById(int selectStaffId);

    boolean update(StaffDO staffDO);

    boolean delete(int[] selectStaffIds);
}
