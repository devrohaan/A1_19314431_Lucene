import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        DataPreprocess dataPreprocess = new DataPreprocess();
        Index index = new Index();
        Scanner in = new Scanner(System.in);

        System.out.println(AppConstants.FORMATTER);
        System.out.println(AppConstants.CYELW_START + AppConstants.NAME + AppConstants.TCDID + AppConstants.COURSE + AppConstants.ASMNT + AppConstants.CYELW_END);
        System.out.println(AppConstants.FORMATTER);
        System.out.println(AppConstants.ANALYZER);
        System.out.print(AppConstants.NEW_LINE);
        System.out.println(AppConstants.TAB + AppConstants.CHOICE_STANDARD);
        System.out.println(AppConstants.TAB + AppConstants.CHOICE_SIMPPLE);
        System.out.println(AppConstants.TAB + AppConstants.CHOICE_WHITESPACE);
        System.out.println(AppConstants.TAB + AppConstants.CHOICE_CUSTOM);

        System.out.print(AppConstants.NEW_LINE);

        System.out.print(AppConstants.ANALYZER_CHOICE);
        while (!in.hasNextInt())
        {
            System.out.println(AppConstants.RED_START + AppConstants.INVALID_CHOICE + AppConstants.RED_END);
            in.next();
        }
        int analyzer = in.nextInt();
        switch (analyzer){
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            default:
                {
                 analyzer = 4;
                System.out.println(AppConstants.RED_START + AppConstants.OUT_OF_RANGE + AppConstants.RED_END);
                System.out.println(AppConstants.CGRN_START + AppConstants.DEFAULT_PARSER + AppConstants.CGRN_END);
            }
        }

        System.out.println(AppConstants.NEW_LINE);

        //Load Data
        ArrayList cranfieldDataArrayList = dataPreprocess.dataLoad();
        //Create Index
        index.createIndex(cranfieldDataArrayList, AppConstants.BM25, analyzer);
        index.createIndex(cranfieldDataArrayList, AppConstants.VSM, analyzer);
        //Load Queries
        ArrayList cranfieldQueryArrayList = dataPreprocess.queryLoad();
        //Load Queries
        index.searchQuery(cranfieldQueryArrayList, AppConstants.BM25, analyzer);
        index.searchQuery(cranfieldQueryArrayList, AppConstants.VSM, analyzer);

    }
}
