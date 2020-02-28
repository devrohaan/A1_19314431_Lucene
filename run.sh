
while :
do
  java -jar target/A1_19314431_Lucene-1.0-SNAPSHOT-jar-with-dependencies.jar
  echo "Performing evaluation using trec_eval."
  echo ""
  ./trec_eval-9.0.7/trec_eval ./data/QRelsCorrectedforTRECeval ./results/BM25.txt > ./Trec_Eval_Results/BM25_Trec_Evaluation.txt
  ./trec_eval-9.0.7/trec_eval ./data/QRelsCorrectedforTRECeval ./results/VectorSpaceModel.txt > ./Trec_Eval_Results/VSM_Trec_Evaluation.txt

  echo "Mean Average Precesion using BM25:"
  echo ""
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '1p'
  echo ""
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '6p'
  echo ""
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '22p'
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '23p'
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '24p'
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '25p'
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '26p'
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '27p'
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '27p'
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '28p'
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '29p'
  cat Trec_Eval_Results/BM25_Trec_Evaluation.txt | sed -n '30p'
  echo ""

  echo "Mean Average Precesion using VectorSpaceModel:"
  echo ""
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '1p'
  echo ""
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '6p'
  echo ""
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '22p'
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '23p'
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '24p'
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '25p'
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '26p'
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '27p'
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '27p'
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '28p'
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '29p'
  cat Trec_Eval_Results/VSM_Trec_Evaluation.txt | sed -n '30p'
  echo ""

  echo "Complete Evaluation Matrix (Precision and Recall) Stored in: Trec_Eval_Results/BM25_Trec_Evaluation.txt"
  echo "Complete Evaluation Matrix (Precision and Recall) Stored in: Trec_Eval_Results/VSM_Trec_Evaluation.txt"
  echo ""
  read -p "Please Enter ( -1 to quit or any integer to continue ) : " a
	if [ $a -eq -1 ]
	then
		break
	fi
done
