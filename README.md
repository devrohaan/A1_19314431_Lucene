#### CS7IS3-A-SEM202-201920-INFORMATION-RETRIEVAL-AND-WEB-SEARCH

Name: **ROHAN DILIP BAGWE**</br>
TCDID: **19314431**</br>
COURSE: **MSc COMPUTER SCIENCE INTELLIGET SYSTEMS**

# Assignment 1: Lucene - A Full-text Search Library

> Apache Lucene is an open source, high-performance, full-text search engine library written completely in Java and
released under the Apache License 2.0. Lucene is suitable for any application or website that requires full-text indexing and searching functionality. This is a short report explaining the design, implementation and performance evaluation of a text-only search engine built on Cranfield collection.

### :paperclip: Cookbook

:pencil2: *To login into AWS console, please find the credentials in the Short Report.*


>  Change to project Directory

    cd A1_19314431_Lucene/

> To clean the project directory 

    mvn clean

>  To build the project directory
    
    mvn install

> To execute the Lucene search engine

    ./run.sh

### :paperclip: run.sh

:pencil2: run.sh internally executes the **A1_19314431_Lucene-1.0-SNAPSHOT-jar-with-dependencies.jar** file created during maven install and located at

        ./target/A1_19314431_Lucene-1.0-SNAPSHOT-jar-with-dependencies.jar
        
:pencil2: It also executes **trec_eval** to compare the results

        ./trec_eval-9.0.7/trec_eval ./data/QRelsCorrectedforTRECeval ./results/BM25.txt > ./Trec_Eval_Results/BM25_Trec_Evaluation.txt
        
        ./trec_eval-9.0.7/trec_eval ./data/QRelsCorrectedforTRECeval ./results/VectorSpaceModel.txt > ./Trec_Eval_Results/VSM_Trec_Evaluation.txt

:pencil2: Indices are stored in **'indexStore'** directory

        ./indexStore/BM25/
        ./indexStore/VectorSpaceModel/
:pencil2: Query search results in **'results'** directory  

        ./results/BM25.txt
        ./results/VectorSpaceModel.txt
        
:pencil2: During the execution, trec_eval results are stored in **'Trec_Eval_Results'** for the given choice of analyzer

        ./Trec_Eval_Results/BM25_Trec_Evaluation.txt
        ./Trec_Eval_Results/VSM_Trec_Evaluation.txt
     
:pencil2: **'Trec_Eval_Results_Archive'** contains trec_eval results of all analyzers

        ./Trec_Eval_Results_Archive/CustomAnalyzer/
        ./Trec_Eval_Results_Archive/SimpleAnalyzer/
        ./Trec_Eval_Results_Archive/StandardAnalyzer/
        ./Trec_Eval_Results_Archive/WhitespaceAnalyzer/
        
:pencil2: Enter **'-1'** at the end of the execution to exit from run.sh.

### :paperclip: Demo
![git](https://github.com/rohan-tcd/A1_19314431_Lucene/blob/master/static/demo.gif) 


### Thank you!
