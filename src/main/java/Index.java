import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;

public class Index {

    public void createIndex(ArrayList<CranfieldData> cranfieldDataList, String indexer, int analyzerChoice) {

        System.out.println(AppConstants.CREATING_INDEX.concat(indexer));
        Instant startTime = Instant.now();
        try {
            Analyzer analyzer;
            if(analyzerChoice == AppConstants.STANDARD_ANALYZER)
                analyzer = new StandardAnalyzer(EnglishAnalyzer.getDefaultStopSet());
            else if (analyzerChoice == AppConstants.SIMPLE_ANALYZER)
                analyzer = new SimpleAnalyzer();
            else if (analyzerChoice == AppConstants.WHITESPACE_ANALYZER)
                analyzer = new WhitespaceAnalyzer();
            else {
                //Custom Analyzer
                analyzer = CustomAnalyzer.builder().withTokenizer(StandardTokenizerFactory.class)
                        .addTokenFilter("stop").addTokenFilter("porterstem").addTokenFilter("capitalization").build();
            }

            Directory directory = FSDirectory.open(Paths.get(AppConstants.INDEX_STORE.concat(indexer)));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            if (indexer.equalsIgnoreCase(AppConstants.BM25))
                config.setSimilarity(new BM25Similarity());
            else if (indexer.equalsIgnoreCase((AppConstants.VSM)))
                config.setSimilarity(new ClassicSimilarity());
            IndexWriter indexWriter = new IndexWriter(directory, config);
            for (CranfieldData cranfieldDataObj: cranfieldDataList) {
                Document document = new Document();
                document.add(new StringField("ID", cranfieldDataObj.getId(), Field.Store.YES));
                document.add(new TextField("TITLE", cranfieldDataObj.getTitle(), Field.Store.YES));
                document.add(new TextField("AUTHOR", cranfieldDataObj.getAuthor(), Field.Store.YES));
                document.add(new TextField("BIBLIOGRAPHY", cranfieldDataObj.getBibliography(), Field.Store.YES));
                document.add(new TextField("TEXT", cranfieldDataObj.getText(), Field.Store.YES));
                indexWriter.addDocument(document);
            }
            indexWriter.close();
            directory.close();

        } catch (IOException e) {
            System.out.println(AppConstants.ERROR_CREATING_INDEX);
            e.printStackTrace();
            System.exit(0);
        }
        Instant finishTime = Instant.now();
        long timeElapsed = Duration.between(startTime, finishTime).toMillis();
        System.out.println(AppConstants.EXECUTION_TIME + timeElapsed + AppConstants.NEW_LINE);
    }


    public void searchQuery(ArrayList<CranfieldQuery> cranfieldQueryList, String indexer, int analyzerChoice) {

        System.out.println(AppConstants.SEARCH_INDEX.concat(indexer));
        Instant startTime = Instant.now();
        String analyzerName;
        try {
            Analyzer analyzer;
            if(analyzerChoice == AppConstants.STANDARD_ANALYZER)
            {
                analyzer = new StandardAnalyzer(EnglishAnalyzer.getDefaultStopSet());
                analyzerName = AppConstants.ST;
            }
            else if (analyzerChoice == AppConstants.SIMPLE_ANALYZER) {
                analyzer = new SimpleAnalyzer();
                analyzerName = AppConstants.SIMT;
            } else if (analyzerChoice == AppConstants.WHITESPACE_ANALYZER) {
                analyzer = new WhitespaceAnalyzer();
                analyzerName = AppConstants.WT;
            }else {
                //Custom Analyzer
                analyzer = CustomAnalyzer.builder().withTokenizer(StandardTokenizerFactory.class)
                        .addTokenFilter("stop").addTokenFilter("porterstem").addTokenFilter("capitalization").build();
                analyzerName = AppConstants.CT;
            }
            Directory directory = FSDirectory.open(Paths.get(AppConstants.INDEX_STORE.concat(indexer)));
            DirectoryReader directoryReader = DirectoryReader.open(directory);
            IndexSearcher indexSearcher = new IndexSearcher(directoryReader);
            if (indexer.equalsIgnoreCase(AppConstants.BM25))
                indexSearcher.setSimilarity(new BM25Similarity());
            else if (indexer.equalsIgnoreCase((AppConstants.VSM)))
                indexSearcher.setSimilarity(new ClassicSimilarity());
            File resultDirectory = new File(AppConstants.RESULT_DIR);
            if (!resultDirectory.exists())
                resultDirectory.mkdirs();
            PrintStream standard = System.out;
            File file = new File(AppConstants.RESULT_DIR.concat(indexer).concat(AppConstants.FILE_EXTENSION));
            PrintStream stream = new PrintStream(file);
            System.setOut(stream);
            int queryCount = 1;
            HashMap<String, Float> boost = new HashMap<String, Float>();
            boost.put("TITLE",0.20f);
            boost.put("AUTHOR",0.05f);
            boost.put("BIBLIOGRAPHY",0.05f);
            boost.put("TEXT",0.70f);
            for(CranfieldQuery cranfieldQueryObj: cranfieldQueryList){
                MultiFieldQueryParser queryParser = new MultiFieldQueryParser(
                        new String[]{"TITLE", "AUTHOR", "BIBLIOGRAPHY", "TEXT"}, analyzer, boost);
                Query query = queryParser.parse(cranfieldQueryObj.getQuery());

                ScoreDoc[] hits = indexSearcher.search(query, AppConstants.PAGE_HIT).scoreDocs;
                for (int i = 0; i < hits.length; i++) {
                    int documentId = hits[i].doc;
                    Document doc = indexSearcher.doc(documentId);
                    System.out.println(queryCount + " 0 " + doc.get("ID")  + " 0 " + hits[i].score +  " " + analyzerName);
                }
                queryCount += 1;
            }
            System.setOut(standard);
            System.out.println(AppConstants.CGRN_START+ AppConstants.RESULTS_STORED.concat(AppConstants.RESULT_DIR).concat(indexer).concat(AppConstants.FILE_EXTENSION) + AppConstants.CGRN_END);
        } catch (Exception e) {
            System.out.println(AppConstants.ERROR_SEARCHING_INDEX);
            e.printStackTrace();
            System.exit(0);
        }
        Instant finishTime = Instant.now();
        long timeElapsed = Duration.between(startTime, finishTime).toMillis();
        System.out.println(AppConstants.EXECUTION_TIME + timeElapsed + AppConstants.NEW_LINE);
    }

}

