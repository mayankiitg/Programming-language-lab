javac ../Logs/Log.java -d ../Logs
javac -cp ../Logs/ ../MergeSortUsingForkJoin/MergeSort.java -d ../MergeSortUsingForkJoin
javac -cp ../Logs/:../MergeSortUsingForkJoin -d . *.java
java -cp .:../Logs/:../MergeSortUsingForkJoin SensorDataFusionUsingSyncKeyword.MainThread $1 $2
# $1 for queue type
# $2 for with/without merge sort