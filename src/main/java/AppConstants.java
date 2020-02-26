public class AppConstants {

    //Data Constants
    public static final String DATA_DIR = "data";
    public static final String RESULT_DIR = "results/";
    public static final String CRANFIELD_DATA = "/cran.all.1400";
    public static final String CRANFIELD_QUERIES = "/cran.qry";
    public static final int PAGE_HIT = 1000;

    //App Constants
    public static final String EXECUTION_TIME = " >> Execution Time in Milliseconds: ";
    public static final String TOTAL_RECORDS = " >> Total number of records parsed: ";

    //Data Loader Constants
    public static final String DATA_LOADING = "1. Loading Data";
    public static final String ERROR_DATA_LOADING = "Error occurred >> Loading Data";
    public static final String QUERY_LOADING = "4. Loading Query";
    public static final String ERROR_QUERY_LOADING = "Error occurred >> Loading Query";

    //Data Preprocess Constants
    public static final String DATA_PRE_PROCESSING = "3. Pre-processing Data";
    public static final String ERROR_DATA_PRE_PROCESSING = "Error occurred >> Pre-processing Data";

    //Data Translate Constants
    public static final String DATA_TRANSLATE = "2. Translating Data";
    public static final String ERROR_DATA_TRANSLATE = "Error occurred >> Translating Data";

    //CranFieldData and CranfieldQuery Constants
    public static final String CRANFIELD_INITIAL_STATE = ".I";
    public static final String ID_STATE = ".I";
    public static final String AUTHOR_STATE = ".A";
    public static final String TITLE_STATE = ".T";
    public static final String BIBLIOGRAPHY_STATE = ".B";
    public static final String TEXT_STATE = ".W";

    //Query Translate Constants
    public static final String QUERY_TRANSLATE = "4. Translating Queries";
    public static final String ERROR_QUERY_TRANSLATE = "Error occurred >> Translating Queries";

    //Index creator
    public static final String INDEX_STORE = "indexStore/";
    public static final String BM25 = "BM25";
    public static final String TF_IDF = "TF-IDF";
    public static final String CREATING_INDEX = "3. Creating index: ";
    public static final String ERROR_CREATING_INDEX = "Error occurred >> Creating Index";

    //Index Search
    public static final String SEARCH_INDEX = "5. Searching index: ";
    public static final String ERROR_SEARCHING_INDEX = "Error occurred >> Searching Index";

    public static final String FILE_EXTENSION = ".txt";
    public static final String VSM = "VectorSpaceModel";
    public static final String RESULTS_STORED = " >> Results are stored in ";

    //Color
    public static final String CYELW_START = "\033[32;1;2m";
    public static final String CYELW_END = "\033[0m";
    public static final String CGRN_START = "\033[34m";
    public static final String CGRN_END = "\033[0m";
    public static final String RED_START = "\033[31;1m";
    public static final String RED_END = "\033[0m";
    //Formatters
    public static final String FORMATTER = "\n ... \n";
    public static final String NEW_LINE = "\n";
    public static final String TAB = "\t";
    //Student Details
    public static final String NAME = "Name: Rohan Dilip Bagwe\n";
    public static final String TCDID = "TCDID: 19314431\n";
    public static final String COURSE = "COURSE: CS7IS3-A-SEM202-201920: INFORMATION RETRIEVAL AND WEB SEARCH\n";
    public static final String ASMNT = "Assignment 1 - Lucene and Cranfield\n";
    public static final String INVALID_CHOICE = "Please enter correct option.";
    public static final String OUT_OF_RANGE = "Option out of range!";
    public static final String ANALYZER = "Please Select Analyzer:";
    public static final String ANALYZER_CHOICE = "Enter your choice:";
    public static final String CHOICE_STANDARD = "1. Standard Analyzer";
    public static final String CHOICE_SIMPPLE = "2. Simple Analyzer";
    public static final String CHOICE_WHITESPACE = "3. Whitespace Analyzer";
    public static final String CHOICE_CUSTOM = "4. Custom Analyzer";

    public static final int STANDARD_ANALYZER = 1;
    public static final int SIMPLE_ANALYZER = 2;
    public static final int WHITESPACE_ANALYZER = 3;
    public static final int CUSTOM_ANALYZER = 4;

    public static final String ST = "STANDARD";
    public static final String SIMT = "SIMPLE";
    public static final String WT = "WHITESPACE";
    public static final String CT = "CUSTOM";

    public static final String DEFAULT_PARSER = "SELECTING DEFAULT: CUSTOM ANALYZER";
}
