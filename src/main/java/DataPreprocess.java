import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DataPreprocess {
    /*
    Reads the data from Directory
    */
    public ArrayList<CranfieldData> dataLoad() {

        System.out.println(AppConstants.DATA_LOADING);
        Instant startTime = Instant.now();
        BufferedReader data = null;
        try {
            File file = new File(AppConstants.DATA_DIR + AppConstants.CRANFIELD_DATA);
            data = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            System.out.println(AppConstants.ERROR_DATA_LOADING);
            e.printStackTrace();
            System.exit(0);
        }
        Instant finishTime = Instant.now();
        long timeElapsed = Duration.between(startTime, finishTime).toMillis();
        System.out.println(AppConstants.EXECUTION_TIME + timeElapsed + AppConstants.NEW_LINE);
        return dataTranslate(data);
    }

    public ArrayList<CranfieldData> dataTranslate(BufferedReader data){

        System.out.println(AppConstants.DATA_TRANSLATE);
        Instant startTime = Instant.now();
        String dataRow;
        String currentState = AppConstants.CRANFIELD_INITIAL_STATE;
        CranfieldData cranfieldDataObj = null;
        ArrayList<CranfieldData> cranfieldDataList=  new ArrayList<CranfieldData>();
        try {
            while ((dataRow = data.readLine()) != null) {

                String[] listOfWords = dataRow.split("\\s+");
                switch (listOfWords[0]){
                    case AppConstants.ID_STATE:
                        cranfieldDataList.add(cranfieldDataObj);
                        cranfieldDataObj = new CranfieldData();
                        currentState = AppConstants.ID_STATE;
                        cranfieldDataObj.setId(listOfWords[1]);
                        break;
                    case AppConstants.TITLE_STATE:
                        currentState = AppConstants.TITLE_STATE;
                        break;
                    case AppConstants.AUTHOR_STATE:
                        currentState = AppConstants.AUTHOR_STATE;
                        break;
                    case AppConstants.BIBLIOGRAPHY_STATE:
                        currentState = AppConstants.BIBLIOGRAPHY_STATE;
                        break;
                    case AppConstants.TEXT_STATE:
                        currentState = AppConstants.TEXT_STATE;
                        break;
                    default:
                        switch (currentState){
                            case AppConstants.TITLE_STATE:
                                cranfieldDataObj.setTitle(cranfieldDataObj.getTitle().concat(dataRow).concat(" "));
                                break;
                            case AppConstants.AUTHOR_STATE:
                                cranfieldDataObj.setAuthor(cranfieldDataObj.getAuthor().concat(dataRow).concat(" "));
                                break;
                            case AppConstants.BIBLIOGRAPHY_STATE:
                                cranfieldDataObj.setBibliography(cranfieldDataObj.getBibliography().concat(dataRow).concat(" "));
                                break;
                            case AppConstants.TEXT_STATE:
                                cranfieldDataObj.setText(cranfieldDataObj.getText().concat(dataRow).concat(" "));
                                break;
                    }
                }

            }
        } catch (IOException e){
            System.out.println(AppConstants.ERROR_DATA_TRANSLATE);
            e.printStackTrace();
            System.exit(0);
        }
        cranfieldDataList.add(cranfieldDataObj);
        Instant finishTime = Instant.now();
        long timeElapsed = Duration.between(startTime, finishTime).toMillis();
        cranfieldDataList.remove(0);
        System.out.println(AppConstants.TOTAL_RECORDS + cranfieldDataList.size());
        System.out.println(AppConstants.EXECUTION_TIME + timeElapsed + AppConstants.NEW_LINE);
        return cranfieldDataList;
    }

    public ArrayList<CranfieldQuery> queryLoad() {

        System.out.println(AppConstants.QUERY_LOADING);
        Instant startTime = Instant.now();
        BufferedReader data = null;
        try {
            File file = new File(AppConstants.DATA_DIR + AppConstants.CRANFIELD_QUERIES);
            data = new BufferedReader(new FileReader(file));
        } catch (IOException e) {
            System.out.println(AppConstants.ERROR_QUERY_LOADING);
            e.printStackTrace();
            System.exit(0);
        }
        Instant finishTime = Instant.now();
        long timeElapsed = Duration.between(startTime, finishTime).toMillis();
        System.out.println(AppConstants.EXECUTION_TIME + timeElapsed + AppConstants.NEW_LINE);
        return queryTranslate(data);
    }


    public ArrayList<CranfieldQuery>queryTranslate(BufferedReader data){

        System.out.println(AppConstants.QUERY_TRANSLATE);
        Instant startTime = Instant.now();
        String dataRow;
        String currentState = AppConstants.CRANFIELD_INITIAL_STATE;
        CranfieldQuery cranfielQuerydObj = null;
        ArrayList<CranfieldQuery> cranfieldQueryList=  new ArrayList<CranfieldQuery>();
        try {
            while ((dataRow = data.readLine()) != null) {
                dataRow = dataRow.replace("?","");
                //dataRow = dataRow.replaceAll("[^a-zA-Z0-9. ]", "");
                String[] listOfWords = dataRow.split("\\s+");
                switch (listOfWords[0]){
                    case AppConstants.ID_STATE:
                        cranfieldQueryList.add(cranfielQuerydObj);
                        cranfielQuerydObj = new CranfieldQuery();
                        currentState = AppConstants.ID_STATE;
                        cranfielQuerydObj.setId(listOfWords[1]);
                        break;
                    case AppConstants.TEXT_STATE:
                        currentState = AppConstants.TEXT_STATE;
                        break;
                    default:
                        switch (currentState) {
                            case AppConstants.TEXT_STATE:
                                cranfielQuerydObj.setQuery(cranfielQuerydObj.getQuery().concat(dataRow).concat(" "));
                                break;
                        }
                }

            }
        } catch (IOException e){
            System.out.println(AppConstants.ERROR_QUERY_TRANSLATE);
            e.printStackTrace();
            System.exit(0);
        }
        cranfieldQueryList.add(cranfielQuerydObj);
        Instant finishTime = Instant.now();
        long timeElapsed = Duration.between(startTime, finishTime).toMillis();
        cranfieldQueryList.remove(0);
        System.out.println(AppConstants.TOTAL_RECORDS + cranfieldQueryList.size());
        System.out.println(AppConstants.EXECUTION_TIME + timeElapsed + AppConstants.NEW_LINE);
        return cranfieldQueryList;
    }

}
