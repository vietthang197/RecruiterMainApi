package com.recruitercorp.controller;

import com.recruitercorp.dto.JobDto;
import com.recruitercorp.dto.ResponseData;
import com.recruitercorp.entity.Job;
import com.recruitercorp.services.JobService;
import org.hibernate.search.backend.lucene.types.predicate.impl.LuceneTextMatchPredicate;
import org.hibernate.search.engine.search.predicate.dsl.PredicateFinalStep;
import org.hibernate.search.engine.search.query.SearchQuery;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.engine.search.query.dsl.SearchQuerySelectStep;
import org.hibernate.search.engine.search.sort.dsl.SearchSortFactory;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.common.EntityReference;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/job/v1")
public class JobController {

    private final JobService jobService;
    private final EntityManagerFactory entityManagerFactory;

    public JobController(JobService jobService, EntityManagerFactory entityManagerFactory) {
        this.jobService = jobService;
        this.entityManagerFactory = entityManagerFactory;
    }

    @GetMapping("/popular-job")
    @Transactional
    public List<Job> getPopularJob(@RequestParam String q) throws InterruptedException {
        EntityManager em = entityManagerFactory.createEntityManager();
        SearchSession searchSession = Search.session(em);

        SearchResult<Job> result = searchSession.search( Job.class )
                .where( f -> f.match()
                        .fields( "title" )
                        .matching( q) )
                .sort(SearchSortFactory::score)
                .fetchAll();

        return result.hits();
    }

    @GetMapping("/test")
    public ResponseData<String> test() {
        return jobService.test();
    }
}
