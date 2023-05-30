package com.recruitercorp.services;

import com.recruitercorp.dto.JobDto;
import com.recruitercorp.dto.ResponseData;

import java.util.List;

public interface JobService {
    ResponseData<List<JobDto>> getPopularJob();

    ResponseData<String> test();
}
