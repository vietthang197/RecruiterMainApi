package com.recruitercorp.bean;

import org.apache.lucene.analysis.charfilter.HTMLStripCharFilterFactory;
import org.apache.lucene.analysis.icu.ICUNormalizer2FilterFactory;
import org.apache.lucene.analysis.icu.segmentation.ICUTokenizerFactory;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilterFactory;
import org.apache.lucene.analysis.ngram.EdgeNGramFilterFactory;
import org.apache.lucene.analysis.ngram.NGramFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext;
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer;
import org.springframework.stereotype.Component;
import org.apache.lucene.analysis.ngram.EdgeNGramTokenizerFactory;

@Component
public class MyAnalysisConfigurer implements LuceneAnalysisConfigurer {

    @Override
    public void configure(LuceneAnalysisConfigurationContext context) {

        context.analyzer("customAnalyzer").custom()
                .tokenizer(ICUTokenizerFactory.class)
                .tokenFilter(ICUNormalizer2FilterFactory.class)
                .tokenFilter(LowerCaseFilterFactory.class)
                .tokenFilter(EdgeNGramFilterFactory.class)
                .param("minGramSize", "3")
                .param("maxGramSize", "10")
                .charFilter(HTMLStripCharFilterFactory.class);
    }
}
