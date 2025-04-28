# isArmBroken

This is a prediction tool for the isArmBroken dataset. This dataset is categorical and contains 4 features with 2 possible values for each feature and a single label being "Yes" or "No".
Frequency Table:
LowImpact Bent Soft Yes ,Arm broken count: 1 ,Arm not broken count: 8

HighImpact Bent Soft Yes ,Arm broken count: 5 ,Arm not broken count: 5

LowImpact Bent Hard Yes ,Arm broken count: 1 ,Arm not broken count: 14

HighImpact Bent Hard Yes ,Arm broken count: 4 ,Arm not broken count: 6

LowImpact Bent Soft No ,Arm broken count: 1 ,Arm not broken count: 9

HighImpact Bent Soft No ,Arm broken count: 1 ,Arm not broken count: 3

LowImpact Extended Soft Yes ,Arm broken count: 0 ,Arm not broken count: 7

HighImpact Extended Soft Yes ,Arm broken count: 6 ,Arm not broken count: 5

LowImpact Bent Hard No ,Arm broken count: 0 ,Arm not broken count: 6

HighImpact Bent Hard No ,Arm broken count: 2 ,Arm not broken count: 7

LowImpact Extended Hard Yes ,Arm broken count: 1 ,Arm not broken count: 7

HighImpact Extended Hard Yes ,Arm broken count: 8 ,Arm not broken count: 5

LowImpact Extended Soft No ,Arm broken count: 1 ,Arm not broken count: 9

HighImpact Extended Soft No ,Arm broken count: 6 ,Arm not broken count: 3

LowImpact Extended Hard No ,Arm broken count: 3 ,Arm not broken count: 10

HighImpact Extended Hard No ,Arm broken count: 6 ,Arm not broken count: 0



## Class description

### FileProcessor Class
FileProcessor is a class for handling basic file operations (read, write ,etc..).

Features
  - File file: is the file which is instantiated in the Constructor
  - String filename: is the path of the file
  - Integer fileLength: is the amount of lines in the file

Functions
 - FileProcessor(): Handles file setup.
 - getFileString(): reads all lines from a file and returns an ArrayList<ArrayList<String>>, where each inner list contains the parsed CSV values of a line.
 - writeLine(String line): appends a given string to the file.
 - getFileLength() : returns integer line length of file
	


### HelperFunctions Class
HelperFunctions is a static class to support data processing tasks, particularly for converting categorical string features into binary formats and splitting datasets for machine learning.

Features
  - HashMap<String, Integer> stringToBinary: Maps each features string format to its integer format e.g. "Yes" : 1, "No": 0 ...
  - Integer trainSplitSize : This is the decimal percentage size of how much of the data will be used for training
  - Integer testSplitSize : This is the decimal percentage size of how much of the data will be used for testing

Functions
  - trainSplitX(ArrayList<ArrayList<Integer>> D) : Takes the integer format of the data features and splits it into the training size of the data and returns the training data.
  - trainSplitY(ArrayList<ArrayList<Integer>> D) : Takes the integer format of the data labels and splits it into the training size of the data and returns the training data.
  - testSplitX(ArrayList<ArrayList<Integer>> D) : Takes the integer format of the data features and splits it into the testing size of the data and returns the testing data.
  - testSplitY(ArrayList<ArrayList<Integer>> D) : Takes the integer format of the data labels and splits it into the testing size of the data and returns the testing data.
  - stringToBinaryData(ArrayList<ArrayList<String>> stringData, int features): Takes a List of string features and integer labels and converts the features into a single integer and puts it into a list of with the label e.g. [1001, 1]. returns list of converted features and label.
  - getArrayCSVLine(String line): Converts a csv line to an array of string
  - binaryToString(int binaryFeature): takes a converted integer feature and reverts it to its string format e.g. 1001 -> "HighImapct,bent,Soft,Yes". It does this by getting the last number and mapping it back to its feature value.



### BayesClassifer Class
BayesClassifier is a class that implements a simple Naive Bayes model for binary classification based on integer-encoded feature data. It supports training, testing, and predicting outcomes based on learned probabilities from training data.

Features
	-	ArrayList<ArrayList> D: The complete dataset containing feature-label pairs in integer format.
	-	ArrayList X_Train: The training dataset’s features, extracted from D.
	-	ArrayList Y_Train: The training dataset’s labels, extracted from D.
	-	ArrayList X_Test: The testing dataset’s features, extracted from D.
	-	ArrayList Y_Test: The testing dataset’s labels, extracted from D.
	-	int features: The number of features.
	-	HashMap<Integer, ArrayList> probabilityMap: The probability map where each key is an integer-represented feature and the value is a two-element list [yesCount, noCount], representing how many times the feature is has label 1 (“yes”) and label 0 (“no”) in the training data.

Functions
	-	BayesClassifier(ArrayList<ArrayList> D, int features): Constructor that initializes the classifier, splits the dataset into training and testing parts, and prepares the internal structures for learning.
	-	void fit(): Trains the model by using the training dataset and filling the probabilityMap with the count of “yes” and “no” for each permutation.
	-	int test(): Tests the model on the testing dataset and returns the prediction accuracy as a integer percentage.
	-	ArrayList predict(int key): Predicts the label for a given encoded integer feature key. Returns an ArrayList where index 0 = predicted label (1 for “yes”, 0 for “no”), index 1 = the confidence percentage of the prediction.


 ### GUI
 The GUI class creates a graphical user interface using the J ui library that allows a user to select the feature options (impact type, arm position, surface type, and protective gear use) using dropdown menus. When the user clicks the “Predict” button, the class encodes the selected options into a numeric key and uses a trained BayesClassifier model to predict whether the user’s arm is predicted to be broken or not, displaying the result and probability.
 


### If I had more time?
If i had more time I would, calcukate the weights of the dataset to see which features had the most impact on the labels, I would also improve the GUI class to make it look better and I would adapt the code to allow it to learn from any dataset.
